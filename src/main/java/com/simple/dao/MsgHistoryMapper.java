package com.simple.dao;


import com.simple.core.MyMapper;
import com.simple.model.entity.MsgHistory;
import com.simple.model.enums.MsgReadType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MsgHistoryMapper extends MyMapper<MsgHistory> {

    @Select("select a.*,b.displayname fromName, c.displayname toName from msg_history a join user b on a.fromId = b.userid join user c on a.toId = c.userid where (fromId = #{fromId} and toId =#{toId}) or (fromId =#{toId} and toId =#{fromId}) order by create_time")
    @ResultMap("BaseResultMap")
    List<MsgHistory> listHistoryByFromIdAndToId(String fromId, String toId);

    @Select({"<script>" ,
            "select count(*) from msg_history where fromId =#{fromId} and toId =#{toId}" ,
            "<if test='readType != null'>",
            " and is_read = #{readType}" ,
            "</if>" ,
            "</script>"})
    int countHistoryByReadType(String fromId, String toId, MsgReadType readType);

    @Select("select a.*,b.displayname fromName, c.displayname toName from msg_history a join user b on a.fromId = b.userid join user c on a.toId = c.userid where (fromId = #{fromId} and toId =#{toId}) or (fromId =#{toId} and toId =#{fromId}) order by create_time desc limit 1")
    @ResultMap("BaseResultMap")
    MsgHistory getLastHistoryByFromIdAndToId(String fromId, String toId);

    @Update("update msg_history set is_read =#{readType} where fromId =#{fromId} and toId=#{toId} and is_read = 0")
    int updateHistoryReadType(String fromId, String toId, MsgReadType readType);


}
