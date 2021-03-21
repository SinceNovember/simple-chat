package com.simple.service.impl;

import com.simple.core.AbstractService;
import com.simple.dao.MsgHistoryMapper;
import com.simple.model.dto.MsgHistoryDTO;
import com.simple.model.entity.MsgHistory;
import com.simple.model.enums.MsgReadType;
import com.simple.service.MsgHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MsgHistoryServiceImpl extends AbstractService<MsgHistory> implements MsgHistoryService {

    @Resource
    private MsgHistoryMapper msgHistoryMapper;

    /**
     * 获取历史记录列表
     * @param fromId
     * @param toId
     * @return
     */
    @Override
    public List<MsgHistory> listMsgHistory(String fromId, String toId) {
        return msgHistoryMapper.listHistoryByFromIdAndToId(fromId, toId);
    }

    /**
     * 统计未读的消息个数
     * @param fromId
     * @param toId
     * @return
     */
    @Override
    public int countNoReadHistory(String fromId, String toId) {
        return msgHistoryMapper.countHistoryByReadType(fromId, toId, MsgReadType.NOT_READ);
    }

    /**
     * 获取最新的一条聊天记录
     * @param fromId
     * @param toId
     * @return
     */
    @Override
    public MsgHistory getLastHistory(String fromId, String toId) {
        return msgHistoryMapper.getLastHistoryByFromIdAndToId(fromId, toId);
    }

    @Override
    public void clearNoReadCount(String fromId, String toId) {
        try {
            msgHistoryMapper.updateHistoryReadType(fromId, toId, MsgReadType.IS_READ);
        } catch (Exception e) {
            log.error("消息记录重置失败");
            e.printStackTrace();
        }
    }

    @Override
    public MsgHistoryDTO convertTo(MsgHistory msgHistory) {
        Assert.notNull(msgHistory,"msgHistory is not null");
        return new MsgHistoryDTO().covertFrom(msgHistory);
    }

    public List<MsgHistoryDTO> convertTo(List<MsgHistory> msgHistories) {
        return msgHistories.stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }


}
