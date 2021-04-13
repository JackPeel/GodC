package com.dbl.godc.service;


import com.dbl.godc.domain.*;

import java.util.List;


public interface GodCService {
    List<ThingSet> selectAllThing();

    UserInstroSet selectuserinstro(String uid);

    List<CommentSet> selectCommentsByThingId(String thingid);

    int selectuser(String uid);

    List<ImageSet> select7HeaderImage();

    int login(String uid, String psw);

    int register(String uid, String psw, String yzm, String ztime, String zt, String uuid);

    int findexist(String uid, String yzm);

    int find(String uid, String psw, String yzm, String ztime, String zt, String uuid);

    int updateuseruuid(String uid, String uuid);

    int changeuserinstro(String uid, String nick, String sex, String age, String city, String headbase64, String instro, String itime, String focus, String fans);

    int updatefocus(String uid, String focus);

    int updatefans(String uid, String fans);

    int addthing(String thing_title, String thing_content, String thing_url, String thing_fromurl, String thing_creattime, String thing_zt);

    int addcomment(String thing_id,String comments_user, String comments_content, String comments_agree, String comments_location, String comments_creattime, String comments_zt);

    int addheaderimage(String image_url);

    int updateagree(String comments_agree, String comments_id);

    int updateuserzt(String uid, String zt);

    String selectuserzt(String uid);

    String selectuseruuid(String uid);

    int getreplysum(String comments_id);

    int addreply(String comments_id, String reply_goal, String reply_user, String reply_content, String reply_agree, String reply_location, String reply_creattime, String reply_zt);

    int updatereplyagree(String reply_agree, String reply_id);

    List<ReplySet> selectReplyByCommentsid(String comments_id);

    ThingSet selectThingById(String thing_id);

    int updatelook(String thing_id, String thing_look);

    int selectreplyagreebyreplyid(String reply_id);

    int selectagreebycommentsid(String comments_id);

    int selectlookbythingid(String thing_id);

    int addcontract(String contract_user, String contract_zt, String contract_purpose, String contract_time);

    ContractSet selectctByuser(String contract_user);

    List<ContractSet> selectContractuser();

    int deleteContractuser(String contract_user);

    int updatectuzt(String contract_user, String contract_zt);

    int updatethingzt(String thing_id, String thing_zt);

    List<UserSet> selectUser();

    List<UserSet> searchuser(String key);

    List<ContractSet> searchcontractuser(String key);

    List<ThingSet> searchthing(String thing_zt ,String key);

    int addofflinemsg(String from_uid, String to_uid, String offline_time, String offline_content, String offline_status);

    List<OfflineSet> getofflinemsgbytouid(String to_uid, String offline_status);

    int updateofflinemsgstatus(String offline_status, String offline_id);
}
