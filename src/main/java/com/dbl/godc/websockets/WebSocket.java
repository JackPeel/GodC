package com.dbl.godc.websockets;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dbl.godc.domain.OfflineSet;
import com.dbl.godc.mapper.GodCMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {
    @Autowired
    private GodCMapper godCMapper;

    public static WebSocket webSocket;

    @PostConstruct
    public void init() {
        webSocket = this;
        webSocket.godCMapper = this.godCMapper;
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;

    private Timer timer = null;

    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        this.username = username;
        this.session = session;
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知，说我上线了
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 1);
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);

            //把自己的信息加入到map当中去
            clients.put(username, this);
            //给自己发一条消息：告诉自己现在都有谁在线
            Map<String, Object> map2 = new HashMap<>();
            map2.put("messageType", 3);
            //移除掉自己
            Set<String> set = clients.keySet();
            map2.put("onlineUsers", set);
            sendMessageTo(JSON.toJSONString(map2), username);

            // 上线
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", username);
            map.put("zt", "0");
            webSocket.godCMapper.updateuserzt(map);

            // 推送所有离线消息
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("to_uid", username);
            map3.put("offline_status", "0");
            List<OfflineSet> offlineSetList = webSocket.godCMapper.getofflinemsgbytouid(map3);
            if (offlineSetList.size() > 0) {
                // 存在未推送离线消息 全部推送
                for (OfflineSet offlineSet : offlineSetList) {
                    Map<String, Object> map4 = new HashMap<String, Object>();
                    map4.put("messageType", 4);
                    map4.put("textMessage", offlineSet.getOffline_content());
                    map4.put("fromusername", offlineSet.getFrom_uid());
                    map4.put("tousername", offlineSet.getTo_uid());
                    sendMessageTo(JSON.toJSONString(map4), offlineSet.getTo_uid());

                    Map<String, Object> map5 = new HashMap<String, Object>();
                    map5.put("offline_status", "1");
                    map5.put("offline_id", offlineSet.getOffline_id());
                    webSocket.godCMapper.updateofflinemsgstatus(map5);
                }
            }
        } catch (IOException e) {
            logger.info(username + "上线的时候通知所有人发生了错误");
        }
        logger.info("用户" + username + "上线了！当前在线人数" + clients.size());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("用户" + username + "强制退出了APP");
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        clients.remove(username);
        if (timer != null) {
            timer.cancel();
        }
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 2);
            map1.put("onlineUsers", clients.keySet());
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);

            // 离线
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", username);
            if (!webSocket.godCMapper.selectuserzt(map).equals("2")) {
                map.put("zt", "1");
                webSocket.godCMapper.updateuserzt(map);
            }
        } catch (IOException e) {
            logger.info(username + "下线的时候通知所有人发生了错误");
        }
        logger.info("用户" + username + "下线了！当前在线人数" + clients.size());
    }

    /**
     * 收到用户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage(maxMessageSize = 10240000)
    public void onMessage(String message, Session session) {
        try {
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromusername = jsonObject.getString("username");
            String tousername = jsonObject.getString("to");
            //如果不是发给所有，那么就发给某一个人
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>();
            if (tousername.equals("All")) {
                if (textMessage.equals("heart")) {
                    map1.put("messageType", 3);
                    Set<String> set = clients.keySet();
                    map1.put("onlineUsers", set);
                    sendMessageTo(JSON.toJSONString(map1), username);
                    if (timer != null) {
                        timer.cancel();
                    }
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        int midTime = 5;

                        public void run() {
                            midTime--;
                            if (midTime <= 0) {
                                timer.cancel();
                                onClose();
                            }
                        }
                    }, 0, 1000);
                } else {
                    map1.put("messageType", 4);
                    map1.put("textMessage", textMessage);
                    map1.put("fromusername", fromusername);
                    map1.put("tousername", "所有人");
                    sendMessageAll(JSON.toJSONString(map1), fromusername);
                }
            } else {
                if (clients.containsKey(tousername)) {
                    //在线消息
                    map1.put("messageType", 4);
                    map1.put("textMessage", textMessage);
                    map1.put("fromusername", fromusername);
                    map1.put("tousername", tousername);
                    sendMessageTo(JSON.toJSONString(map1), tousername);
                } else {
                    //离线消息
                    Map<String, Object> offlinemap = new HashMap<String, Object>();
                    offlinemap.put("from_uid", fromusername);
                    offlinemap.put("to_uid", tousername);
                    offlinemap.put("offline_time", getNowDate());
                    offlinemap.put("offline_content", textMessage);
                    offlinemap.put("offline_status", "0"); // 0 未推送  1 已推送
                    if (webSocket.godCMapper.addofflinemsg(offlinemap) == 1) {
                        logger.info("收到离线消息，已保存到数据库！");
                    }
                }

            }
        } catch (Exception e) {
            logger.info("收到图片");
        }

    }


    public void sendMessageTo(String message, String ToUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.username.equals(ToUserName)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    public void sendMessageAll(String message, String FromUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            try {
                item.session.getAsyncRemote().sendText(message);
            } catch (Exception ignored) {
            }
        }
    }

    public String getNowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return df.format(new Date());
    }


    private long getTimeDifference(String strTime1, String strTime2) {
        //格式日期格式，在此我用的是"2018-01-24 19:49:50"这种格式
        //可以更改为自己使用的格式，例如：yyyy/MM/dd HH:mm:ss 。。。
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date now = df.parse(strTime1);
            Date date = df.parse(strTime2);
            long l = now.getTime() - date.getTime();       //获取时间差
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            return (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}