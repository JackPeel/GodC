package com.dbl.godc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dbl.godc.domain.CommentSet;
import com.dbl.godc.domain.Msg;
import com.dbl.godc.domain.ThingSet;
import com.dbl.godc.service.GodCService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class GodCController {
    @Autowired
    private GodCService gocService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public Msg login(HttpServletRequest request) {
        String userid = request.getParameter("uid");
        String password = request.getParameter("psw");
        Msg mag = new Msg();
        if (userid == null || userid.equals("") || password == null || password.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        if (gocService.login(userid, password) == 0) {
            mag.setMsg("fail");
            mag.setText("登录失败，账号或密码错误！");
        } else {
            mag.setMsg("success");
            mag.setText("登录成功！欢迎回来。");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public Object register(HttpServletRequest request) {
        String userid = request.getParameter("uid");
        String password = request.getParameter("psw");
        String yzm = request.getParameter("yzm");
        String ztime = request.getParameter("ztime");
        String zt = request.getParameter("zt");
        String uuid = request.getParameter("uuid");
        Msg mag = new Msg();
        if (uuid == null || uuid.equals("") || yzm == null || yzm.equals("") || userid == null || userid.equals("") || password == null || password.equals("") || ztime == null || ztime.equals("") || zt == null || zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        if (gocService.selectuser(userid) == 1) {
            mag.setMsg("fail");
            mag.setText("该账号已被使用！");
            return mag;
        }
        try {
            if (gocService.register(userid, password, yzm, ztime, zt, uuid) == 1) {
                mag.setMsg("success");
                mag.setText("注册成功！");
            } else {
                mag.setMsg("fail");
                mag.setText("注册失败！未知异常。");
            }
        } catch (Exception e) {
            mag.setMsg("fail");
            mag.setText("唯一码已被使用！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/findexist")
    public Msg findexist(HttpServletRequest request) {
        String userid = request.getParameter("uid");
        String yzm = request.getParameter("yzm");
        Msg mag = new Msg();
        if (userid == null || userid.equals("") || yzm == null || yzm.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        if (gocService.selectuser(userid) == 0) {
            mag.setMsg("fail");
            mag.setText("该账号不存在！");
            return mag;
        }
        if (gocService.findexist(userid, yzm) == 0) {
            mag.setMsg("fail");
            mag.setText("唯一码错误！");
        } else {
            mag.setMsg("success");
            mag.setText("验证成功。");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/find")
    public Object find(HttpServletRequest request) {
        String userid = request.getParameter("uid");
        String password = request.getParameter("psw");
        String yzm = request.getParameter("yzm");
        String ztime = request.getParameter("ztime");
        String zt = request.getParameter("zt");
        String uuid = request.getParameter("uuid");
        Msg mag = new Msg();
        if (uuid == null || uuid.equals("") || yzm == null || yzm.equals("") || userid == null || userid.equals("") || password == null || password.equals("") || ztime == null || ztime.equals("") || zt == null || zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        if (gocService.find(userid, password, yzm, ztime, zt, uuid) == 2) {
            mag.setMsg("success");
            mag.setText("密码重置成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("密码重置失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/selectAllThing")
    public Object selectAllThing(HttpServletRequest request) {
        return gocService.selectAllThing();
    }

    @ResponseBody
    @RequestMapping(value = "/getHeaderImage")
    public Object getHeaderImage(HttpServletRequest request) {
        return gocService.select7HeaderImage();
    }

    @ResponseBody
    @RequestMapping(value = "/selectContractuser")
    public Object selectContractuser(HttpServletRequest request) {
        return gocService.selectContractuser();
    }

    @ResponseBody
    @RequestMapping(value = "/selectUser")
    public Object selectUser(HttpServletRequest request) {
        return gocService.selectUser();
    }

    @ResponseBody
    @RequestMapping(value = "/searchuser")
    public Object searchuser(HttpServletRequest request) {
        String key = request.getParameter("key");
        return gocService.searchuser("%" + key + "%");
    }

    @ResponseBody
    @RequestMapping(value = "/searchcontractuser")
    public Object searchcontractuser(HttpServletRequest request) {
        String key = request.getParameter("key");
        return gocService.searchcontractuser("%" + key + "%");
    }

    @ResponseBody
    @RequestMapping(value = "/searchthing")
    public Object searchthing(HttpServletRequest request) {
        String thing_zt = request.getParameter("thing_zt");
        String key = request.getParameter("key");
        Msg mag = new Msg();
        if (thing_zt == null || thing_zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        return gocService.searchthing(thing_zt, "%" + key + "%");
    }

    @ResponseBody
    @RequestMapping(value = "/deleteContractuser")
    public Object deleteContractuser(HttpServletRequest request) {
        String contract_user = request.getParameter("contract_user");
        Msg mag = new Msg();
        if (contract_user == null || contract_user.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }

        int rows = gocService.deleteContractuser(contract_user);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("签约驳回成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("签约驳回失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/updatectuzt")
    public Object updatectuzt(HttpServletRequest request) {
        String contract_user = request.getParameter("contract_user");
        String contract_zt = request.getParameter("contract_zt");
        Msg mag = new Msg();
        if (contract_user == null || contract_user.equals("") || contract_zt == null || contract_zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.updatectuzt(contract_user, contract_zt);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("签约状态更新成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("签约状态更新失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/updatethingzt")
    public Object updatethingzt(HttpServletRequest request) {
        String thing_id = request.getParameter("thing_id");
        String thing_zt = request.getParameter("thing_zt");
        Msg mag = new Msg();
        if (thing_id == null || thing_id.equals("") || thing_zt == null || thing_zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.updatethingzt(thing_id, thing_zt);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("文章状态更新成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("文章状态更新失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/selectAllThingByPage")
    public Object selectAllThingByPage(HttpServletRequest request) {
        String currPage = request.getParameter("page");
        Msg mag = new Msg();
        if (currPage == null || currPage.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        List<ThingSet> thinglist = gocService.selectAllThing();
        List<ThingSet> truththinglist = new ArrayList<ThingSet>();
        for (ThingSet thingSet : thinglist) {
            if (thingSet.getThing_zt().equals("0")) {
                truththinglist.add(thingSet);
            }
        }
        // 从第几条数据开始
        int firstIndex = 0;
        // 到第几条数据结束
        int lastIndex = Math.min(truththinglist.size(), Integer.parseInt(currPage) * 5);
        return truththinglist.subList(firstIndex, lastIndex);
    }

    @ResponseBody
    @RequestMapping(value = "/selectThingById")
    public Object selectThingById(HttpServletRequest request) {
        String thing_id = request.getParameter("thing_id");
        Msg mag = new Msg();
        if (thing_id == null || thing_id.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        return gocService.selectThingById(thing_id);
    }

    @ResponseBody
    @RequestMapping(value = "/selectCommentsByThingId")
    public Object selectCommentsByThingId(HttpServletRequest request) {
        String thingid = request.getParameter("thingid");
        String currPage = request.getParameter("page");
        Msg mag = new Msg();
        if (thingid == null || thingid.equals("") || currPage == null || currPage.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        List<CommentSet> commentslist = gocService.selectCommentsByThingId(thingid);
        List<CommentSet> truthcommentslist = new ArrayList<CommentSet>();
        for (CommentSet commentSet : commentslist) {
            if (commentSet.getComments_zt().equals("0")) {
                truthcommentslist.add(commentSet);
            }
        }
        // 从第几条数据开始
        int firstIndex = (Integer.parseInt(currPage) - 1) * 4;
        // 到第几条数据结束
        int lastIndex = Math.min(truthcommentslist.size(), Integer.parseInt(currPage) * 4);

        mag.setMsg("success");
        mag.setText("请求成功！");
        JSONObject data = new JSONObject();
        data.put("commentSum", truthcommentslist.size());
        int commentPage = truthcommentslist.size() % 4 == 0 ? truthcommentslist.size() / 4 : truthcommentslist.size() / 4 + 1;
        data.put("commentPage", commentPage);
        data.put("commentList", truthcommentslist.subList(firstIndex, lastIndex));
        mag.setData(data);
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/selectuserinstro")
    public Object selectuserinstro(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        Msg mag = new Msg();
        if (uid == null || uid.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        return gocService.selectuserinstro(uid);
    }

    @ResponseBody
    @RequestMapping(value = "/changeuserinstro")
    public Object changeuserinstro(HttpServletRequest request) {
        String userid = request.getParameter("uid");
        String nick = request.getParameter("nick");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String city = request.getParameter("city");
        String headbase64 = request.getParameter("headbase64");
        String instro = request.getParameter("instro");
        String itime = request.getParameter("itime");
        String focus = request.getParameter("focus");
        String fans = request.getParameter("fans");
        Msg mag = new Msg();
        if (sex == null || sex.equals("") || userid == null || userid.equals("") || age == null || age.equals("") || city == null || city.equals("") || headbase64 == null || headbase64.equals("") || itime == null || itime.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.changeuserinstro(userid, nick, sex, age, city, headbase64, instro, itime, focus, fans);
        if (rows == 1 || rows == 2) {
            mag.setMsg("success");
            mag.setText("个人信息修改成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("个人信息修改失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/updatefocus")
    public Object updatefocus(HttpServletRequest request) {
        String focus = request.getParameter("focus");
        String uid = request.getParameter("uid");
        Msg mag = new Msg();
        if (uid == null || uid.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.updatefocus(uid, focus);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("关注成功2！");
        } else {
            mag.setMsg("fail");
            mag.setText("关注失败2！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/updatefans")
    public Object updatefans(HttpServletRequest request) {
        String fans = request.getParameter("fans");
        String uid = request.getParameter("uid");
        Msg mag = new Msg();
        if (uid == null || uid.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.updatefans(uid, fans);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("关注成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("关注失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/updateuseruuid")
    public Object updateuseruuid(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        String uuid = request.getParameter("uuid");
        Msg mag = new Msg();
        if (uid == null || uid.equals("") || uuid == null || uuid.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.updateuseruuid(uid, uuid);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("UUID更新成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("UUID更新失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/selectuseruuid")
    public Object selectuseruuid(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        Msg mag = new Msg();
        if (uid == null || uid.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        return gocService.selectuseruuid(uid);
    }

    @ResponseBody
    @RequestMapping(value = "/updatelook")
    public Object updatelook(HttpServletRequest request) {
        String thing_id = request.getParameter("thing_id");
        Msg mag = new Msg();
        if (thing_id == null || thing_id.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int thing_look = gocService.selectlookbythingid(thing_id);
        int rows = gocService.updatelook(thing_id, String.valueOf(thing_look + 1));
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("阅读+1！");
        } else {
            mag.setMsg("fail");
            mag.setText("阅读失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/addthing")
    public Object addthing(HttpServletRequest request) {
        String thing_title = request.getParameter("thing_title");
        String thing_content = request.getParameter("thing_content");
        String thing_url = request.getParameter("thing_url");
        String thing_fromurl = request.getParameter("thing_fromurl");
        String thing_creattime = request.getParameter("thing_creattime");
        String thing_zt = request.getParameter("thing_zt");
        Msg mag = new Msg();
        if (thing_title == null || thing_title.equals("") || thing_content == null || thing_content.equals("") || thing_fromurl == null || thing_fromurl.equals("") || thing_creattime == null || thing_creattime.equals("") || thing_zt == null || thing_zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.addthing(thing_title, thing_content, thing_url, thing_fromurl, thing_creattime, thing_zt);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("文章上传成功！");
            System.out.println(thing_title + "|" + thing_creattime);
        } else {
            mag.setMsg("fail");
            mag.setText("文章上传失败！");
            System.out.println(thing_title + "|" + thing_creattime);
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/addcomment")
    public Object addcomment(HttpServletRequest request) {
        String thing_id = request.getParameter("thing_id");
        String comments_user = request.getParameter("comments_user");
        String comments_content = request.getParameter("comments_content");
        String comments_agree = request.getParameter("comments_agree");
        String comments_location = request.getParameter("comments_location");
        String comments_creattime = request.getParameter("comments_creattime");
        String comments_zt = request.getParameter("comments_zt");
        Msg mag = new Msg();
        if (thing_id == null || thing_id.equals("") || comments_user == null || comments_user.equals("") || comments_content == null || comments_content.equals("") || comments_agree == null || comments_agree.equals("") || comments_location == null || comments_location.equals("") || comments_creattime == null || comments_creattime.equals("") || comments_zt == null || comments_zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.addcomment(thing_id, comments_user, comments_content, comments_agree, comments_location, comments_creattime, comments_zt);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("评论发表成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("评论发表失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/updateagree")
    public Object updateagree(HttpServletRequest request) {
        String comments_id = request.getParameter("comments_id");
        Msg mag = new Msg();
        if (comments_id == null || comments_id.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int comments_agree = gocService.selectagreebycommentsid(comments_id);
        int rows = gocService.updateagree(String.valueOf(comments_agree + 1), comments_id);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("点赞成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("点赞失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/updateuserzt")
    public Object updateuserzt(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        String zt = request.getParameter("zt");
        Msg mag = new Msg();
        if (uid == null || uid.equals("") || zt == null || zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.updateuserzt(uid, zt);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("用户状态更新成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("用户状态更新失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/selectuserzt")
    public Object selectuserzt(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        Msg mag = new Msg();
        if (uid == null || uid.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        if (gocService.selectuser(uid) == 0) {
            mag.setMsg("fail");
            mag.setText("该账号不存在！");
        } else {
            mag.setMsg("success");
            mag.setText(gocService.selectuserzt(uid));
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/getreplysum")
    public int getreplysum(HttpServletRequest request) {
        String comments_id = request.getParameter("comments_id");
        if (comments_id == null || comments_id.equals("")) {
            return 0;
        }
        return gocService.getreplysum(comments_id);
    }

    @ResponseBody
    @RequestMapping(value = "/addreply")
    public Object addreply(HttpServletRequest request) {
        String comments_id = request.getParameter("comments_id");
        String reply_goal = request.getParameter("reply_goal");
        String reply_user = request.getParameter("reply_user");
        String reply_content = request.getParameter("reply_content");
        String reply_agree = request.getParameter("reply_agree");
        String reply_location = request.getParameter("reply_location");
        String reply_creattime = request.getParameter("reply_creattime");
        String reply_zt = request.getParameter("reply_zt");
        Msg mag = new Msg();
        if (comments_id == null || comments_id.equals("") ||
                reply_goal == null || reply_goal.equals("") || reply_user == null || reply_user.equals("") ||
                reply_content == null || reply_content.equals("") || reply_agree == null || reply_agree.equals("")
                || reply_location == null || reply_location.equals("") || reply_creattime == null || reply_creattime.equals("")
                || reply_zt == null || reply_zt.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.addreply(comments_id, reply_goal, reply_user, reply_content, reply_agree, reply_location, reply_creattime, reply_zt);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("回复成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("回复失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/updatereplyagree")
    public Object updatereplyagree(HttpServletRequest request) {
        String reply_id = request.getParameter("reply_id");
        Msg mag = new Msg();
        if (reply_id == null || reply_id.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int reply_agree = gocService.selectreplyagreebyreplyid(reply_id);
        int rows = gocService.updatereplyagree(String.valueOf(reply_agree + 1), reply_id);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("点赞成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("点赞失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/selectReplyByCommentsid")
    public Object selectReplyByCommentsid(HttpServletRequest request) {
        String comments_id = request.getParameter("comments_id");
        Msg mag = new Msg();
        if (comments_id == null || comments_id.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        return gocService.selectReplyByCommentsid(comments_id);
    }

    @ResponseBody
    @RequestMapping(value = "/addcontract")
    public Object addcontract(HttpServletRequest request) {
        String contract_user = request.getParameter("contract_user");
        String contract_zt = request.getParameter("contract_zt");
        String contract_purpose = request.getParameter("contract_purpose");
        String contract_time = request.getParameter("contract_time");
        Msg mag = new Msg();
        if (contract_user == null || contract_user.equals("") || contract_zt == null || contract_zt.equals("") || contract_purpose == null || contract_purpose.equals("") || contract_time == null || contract_time.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.addcontract(contract_user, contract_zt, contract_purpose, contract_time);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("签约请求发送成功，请等待管理员审核！");
        } else {
            mag.setMsg("fail");
            mag.setText("签约请求发送失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/addheaderimage")
    public Object addheaderimage(HttpServletRequest request) {
        String image_url = request.getParameter("image_url");
        Msg mag = new Msg();
        if (image_url == null || image_url.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        int rows = gocService.addheaderimage(image_url);
        if (rows == 1) {
            mag.setMsg("success");
            mag.setText("首页轮播图片添加成功！");
        } else {
            mag.setMsg("fail");
            mag.setText("首页轮播图片添加失败！");
        }
        return mag;
    }

    @ResponseBody
    @RequestMapping(value = "/selectctByuser")
    public Object selectctByuser(HttpServletRequest request) {
        String contract_user = request.getParameter("contract_user");
        Msg mag = new Msg();
        if (contract_user == null || contract_user.equals("")) {
            mag.setMsg("fail");
            mag.setText("参数有误！");
            return mag;
        }
        return gocService.selectctByuser(contract_user);
    }

    public String getNowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return df.format(new Date());
    }

    @PostMapping("/upload")
    public Object upload(HttpServletRequest request) throws IOException {
        List<String> picList = new ArrayList<String>();
        String fileSavePath = "C:/file_dir/";
        File dir = new File(fileSavePath);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        if (multipartHttpServletRequest != null) {
            Iterator<String> files = multipartHttpServletRequest.getFileNames();
            while (files.hasNext()) {
                MultipartFile multipartFile = multipartHttpServletRequest.getFile(files.next());
                assert multipartFile != null;
                String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(fileSavePath + File.separator + newFileName));
                String filePath = request.getScheme() + "://" +
                        request.getServerName() + ":"
                        + request.getServerPort()
                        + "/file_dir/" + newFileName;
//                System.out.println("file文件本地路径：" + fileSavePath + File.separator + newFileName);
//                System.out.println("file文件网络路径:" + filePath);
                picList.add(filePath);
            }
            return picList;
        }
        return "upload_fail";
    }

    public String showMzSm() {
        return "免责声明：本软件仅供个人参考及交流学习使用，不得用于任何非法违法用途，否则一切后果自负，与作者无关。";
    }
}
