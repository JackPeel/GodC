package com.dbl.godc.mapper;


import com.dbl.godc.domain.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GodCMapper {
    List<ThingSet> selectAllThing();

    List<CommentSet> selectCommentsByThingId(Map<String, Object> map);

    int selectuser(Map<String, Object> map);

    int register(Map<String, Object> map);

    int findexist(Map<String, Object> map);

    int find(Map<String, Object> map);

    int changeuserinstro(Map<String, Object> map);

    int addthing(Map<String, Object> map);

    int addcomment(Map<String, Object> map);

    int login(Map<String, Object> map);

    int updateagree(Map<String, Object> map);

    UserInstroSet selectuserinstro(Map<String, Object> map);

    int updateuserzt(Map<String, Object> map);

    String selectuserzt(Map<String, Object> map);

    int getreplysum(Map<String, Object> map);

    int addreply(Map<String, Object> map);

    int updatereplyagree(Map<String, Object> map);

    List<ReplySet> selectReplyByCommentsid(Map<String, Object> map);

    ThingSet selectThingById(Map<String, Object> map);

    int updatefans(Map<String, Object> map);

    int updatefocus(Map<String, Object> map);

    int updatelook(Map<String, Object> map);

    int selectagreebycommentsid(Map<String, Object> map);

    int selectreplyagreebyreplyid(Map<String, Object> map);

    int selectlookbythingid(Map<String, Object> map);

    int updateuseruuid(Map<String, Object> map);

    String selectuseruuid(Map<String, Object> map);

    int addcontract(Map<String, Object> map);

    ContractSet selectctByuser(Map<String, Object> map);

    List<ContractSet> selectContractuser();

    int deleteContractuser(Map<String, Object> map);

    int updatectuzt(Map<String, Object> map);

    int updatethingzt(Map<String, Object> map);

    List<UserSet> selectUser();

    List<UserSet> searchuser(Map<String, Object> map);

    List<ContractSet> searchcontractuser(Map<String, Object> map);

    List<ThingSet> searchthing(Map<String, Object> map);

    int addofflinemsg(Map<String, Object> map);

    List<OfflineSet> getofflinemsgbytouid(Map<String, Object> map);

    int updateofflinemsgstatus(Map<String, Object> map);

    List<ImageSet> getheaderimage();

    int addheaderimage(Map<String, Object> map);
}
