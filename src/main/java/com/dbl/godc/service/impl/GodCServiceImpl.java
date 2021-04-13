package com.dbl.godc.service.impl;


import com.dbl.godc.domain.*;
import com.dbl.godc.mapper.GodCMapper;
import com.dbl.godc.service.GodCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("qyqsetService")
public class GodCServiceImpl implements GodCService {

    @Autowired
    private GodCMapper qyqsetMapper;

    @Override
    public List<ThingSet> selectAllThing() {
        return qyqsetMapper.selectAllThing();
    }

    @Override
    public UserInstroSet selectuserinstro(String uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        return qyqsetMapper.selectuserinstro(map);
    }

    @Override
    public List<CommentSet> selectCommentsByThingId(String thingid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thing_id", thingid);
        return qyqsetMapper.selectCommentsByThingId(map);
    }

    @Override
    public int selectuser(String uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        return qyqsetMapper.selectuser(map);
    }

    @Override
    public List<ImageSet> select7HeaderImage() {
        return qyqsetMapper.getheaderimage();
    }

    @Override
    public int login(String uid, String psw) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("psw", psw);
        return qyqsetMapper.login(map);
    }

    @Override
    public int register(String uid, String psw, String yzm, String ztime, String zt, String uuid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("psw", psw);
        map.put("yzm", yzm);
        map.put("ztime", ztime);
        map.put("zt", zt);
        map.put("uuid", uuid);
        return qyqsetMapper.register(map);
    }

    @Override
    public int findexist(String uid, String yzm) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("yzm", yzm);
        return qyqsetMapper.findexist(map);
    }

    @Override
    public int find(String uid, String psw, String yzm, String ztime, String zt, String uuid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("psw", psw);
        map.put("yzm", yzm);
        map.put("ztime", ztime);
        map.put("zt", zt);
        map.put("uuid", uuid);
        return qyqsetMapper.find(map);
    }


    @Override
    public int updateuseruuid(String uid, String uuid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("uuid", uuid);
        return qyqsetMapper.updateuseruuid(map);
    }

    @Override
    public int changeuserinstro(String uid, String nick, String sex, String age, String city, String headbase64, String instro, String itime, String focus, String fans) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("nick", nick);
        map.put("sex", sex);
        map.put("age", age);
        map.put("city", city);
        map.put("headbase64", headbase64);
        map.put("instro", instro);
        map.put("itime", itime);
        map.put("focus", focus);
        map.put("fans", fans);
        return qyqsetMapper.changeuserinstro(map);
    }

    @Override
    public int updatefocus(String uid, String focus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("focus", focus);
        return qyqsetMapper.updatefocus(map);
    }

    @Override
    public int updatefans(String uid, String fans) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("fans", fans);
        return qyqsetMapper.updatefans(map);
    }

    @Override
    public int addthing(String thing_title, String thing_content, String thing_url, String thing_fromurl, String thing_creattime, String thing_zt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thing_title", thing_title);
        map.put("thing_content", thing_content);
        map.put("thing_url", thing_url);
        map.put("thing_fromurl", thing_fromurl);
        map.put("thing_creattime", thing_creattime);
        map.put("thing_zt", thing_zt);
        return qyqsetMapper.addthing(map);
    }

    @Override
    public int addcomment(String thing_id, String comments_user, String comments_content, String comments_agree, String comments_location, String comments_creattime, String comments_zt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thing_id", thing_id);
        map.put("comments_user", comments_user);
        map.put("comments_content", comments_content);
        map.put("comments_agree", comments_agree);
        map.put("comments_location", comments_location);
        map.put("comments_creattime", comments_creattime);
        map.put("comments_zt", comments_zt);
        return qyqsetMapper.addcomment(map);
    }

    @Override
    public int addheaderimage(String image_url) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image_url", image_url);
        return qyqsetMapper.addheaderimage(map);
    }

    @Override
    public int updateagree(String comments_agree, String comments_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("comments_agree", comments_agree);
        map.put("comments_id", comments_id);
        return qyqsetMapper.updateagree(map);
    }

    @Override
    public int updateuserzt(String uid, String zt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("zt", zt);
        return qyqsetMapper.updateuserzt(map);
    }

    @Override
    public String selectuserzt(String uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        return qyqsetMapper.selectuserzt(map);
    }


    @Override
    public String selectuseruuid(String uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", uid);
        return qyqsetMapper.selectuseruuid(map);
    }

    @Override
    public int getreplysum(String comments_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("comments_id", comments_id);
        return qyqsetMapper.getreplysum(map);
    }

    @Override
    public int addreply(String comments_id, String reply_goal, String reply_user, String reply_content, String reply_agree, String reply_location, String reply_creattime, String reply_zt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("comments_id", comments_id);
        map.put("reply_goal", reply_goal);
        map.put("reply_user", reply_user);
        map.put("reply_content", reply_content);
        map.put("reply_agree", reply_agree);
        map.put("reply_location", reply_location);
        map.put("reply_creattime", reply_creattime);
        map.put("reply_zt", reply_zt);
        return qyqsetMapper.addreply(map);
    }

    @Override
    public int updatereplyagree(String reply_agree, String reply_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("reply_agree", reply_agree);
        map.put("reply_id", reply_id);
        return qyqsetMapper.updatereplyagree(map);
    }

    @Override
    public List<ReplySet> selectReplyByCommentsid(String comments_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("comments_id", comments_id);
        return qyqsetMapper.selectReplyByCommentsid(map);
    }

    @Override
    public ThingSet selectThingById(String thing_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thing_id", thing_id);
        return qyqsetMapper.selectThingById(map);
    }

    @Override
    public int updatelook(String thing_id, String thing_look) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thing_id", thing_id);
        map.put("thing_look", thing_look);
        return qyqsetMapper.updatelook(map);
    }

    @Override
    public int selectreplyagreebyreplyid(String reply_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("reply_id", reply_id);
        return qyqsetMapper.selectreplyagreebyreplyid(map);
    }

    /**
     * 通过评论ID查询点赞数量
     *
     * @param comments_id 评论ID
     * @return 点赞数量
     */
    @Override
    public int selectagreebycommentsid(String comments_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("comments_id", comments_id);
        return qyqsetMapper.selectagreebycommentsid(map);
    }

    /**
     * 通过文章ID查找阅读数
     *
     * @param thing_id 文章ID
     * @return 阅读数
     */
    @Override
    public int selectlookbythingid(String thing_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thing_id", thing_id);
        return qyqsetMapper.selectlookbythingid(map);
    }

    /**
     * 用户签约
     *
     * @param contract_user    用户账号
     * @param contract_zt      用户签约状态
     * @param contract_purpose 用户签约描述
     * @param contract_time    用户签约时间
     * @return 签约返回
     */
    @Override
    public int addcontract(String contract_user, String contract_zt, String contract_purpose, String contract_time) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contract_user", contract_user);
        map.put("contract_zt", contract_zt);
        map.put("contract_purpose", contract_purpose);
        map.put("contract_time", contract_time);
        return qyqsetMapper.addcontract(map);
    }

    /**
     * 通过用户账号查询签约数据
     *
     * @param contract_user 用户账号
     * @return 签约数据
     */
    @Override
    public ContractSet selectctByuser(String contract_user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contract_user", contract_user);
        return qyqsetMapper.selectctByuser(map);
    }

    /**
     * 获取所有签约列表数据
     *
     * @return 签约列表
     */
    @Override
    public List<ContractSet> selectContractuser() {
        return qyqsetMapper.selectContractuser();
    }

    /**
     * 通过用户账号删除签约数据
     *
     * @param contract_user 用户账号
     * @return 签约数据
     */
    @Override
    public int deleteContractuser(String contract_user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contract_user", contract_user);
        return qyqsetMapper.deleteContractuser(map);
    }

    /**
     * 更新签约数据状态
     *
     * @param contract_user 用户账号
     * @param contract_zt   签约状态
     * @return 修改状态
     */
    @Override
    public int updatectuzt(String contract_user, String contract_zt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contract_user", contract_user);
        map.put("contract_zt", contract_zt);
        return qyqsetMapper.updatectuzt(map);
    }

    /**
     * 更新文章数据状态
     *
     * @param thing_id 文章ID
     * @param thing_zt 文章状态
     * @return 修改状态
     */
    @Override
    public int updatethingzt(String thing_id, String thing_zt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thing_id", thing_id);
        map.put("thing_zt", thing_zt);
        return qyqsetMapper.updatethingzt(map);
    }

    /**
     * 获取所有用户列表数据
     *
     * @return 用户列表
     */
    @Override
    public List<UserSet> selectUser() {
        return qyqsetMapper.selectUser();
    }

    /**
     * 通过账号搜索用户
     *
     * @return 用户列表
     */
    @Override
    public List<UserSet> searchuser(String key) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        return qyqsetMapper.searchuser(map);
    }

    /**
     * 通过账号搜索签约数据
     *
     * @return 签约用户列表
     */
    @Override
    public List<ContractSet> searchcontractuser(String key) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        return qyqsetMapper.searchcontractuser(map);
    }

    /**
     * 通过输入值搜索文章数据
     *
     * @return 文章列表
     */
    @Override
    public List<ThingSet> searchthing(String thing_zt, String key) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thing_zt", thing_zt);
        map.put("key", key);
        return qyqsetMapper.searchthing(map);
    }

    /**
     * 保存离线消息
     * @param from_uid 发送ID
     * @param to_uid 接受ID
     * @param offline_time 发送时间
     * @param offline_content 发送内容
     * @param offline_status 消息状态
     * @return 是否保存成功
     */
    @Override
    public int addofflinemsg(String from_uid, String to_uid, String offline_time, String offline_content, String offline_status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("from_uid", from_uid);
        map.put("to_uid", to_uid);
        map.put("offline_time", offline_time);
        map.put("offline_content", offline_content);
        map.put("offline_status", offline_status);
        return qyqsetMapper.addofflinemsg(map);
    }

    /**
     * 获取待发送离线消息列表
     * @param to_uid 目标UID
     * @param offline_status 消息状态
     * @return 离线消息列表
     */
    @Override
    public List<OfflineSet> getofflinemsgbytouid(String to_uid, String offline_status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("to_uid", to_uid);
        map.put("offline_status", offline_status);
        return qyqsetMapper.getofflinemsgbytouid(map);
    }

    /**
     * 更新离线消息状态
     * @param offline_status 消息状态
     * @param offline_id 消息ID
     * @return 是否更新成功
     */
    @Override
    public int updateofflinemsgstatus(String offline_status, String offline_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offline_status", offline_status);
        map.put("offline_id", offline_id);
        return qyqsetMapper.updateofflinemsgstatus(map);
    }
}
