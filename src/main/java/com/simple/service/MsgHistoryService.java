package com.simple.service;

import com.simple.core.Service;
import com.simple.model.dto.MsgHistoryDTO;
import com.simple.model.entity.MsgHistory;
import org.springframework.lang.NonNull;

import java.util.List;

public interface MsgHistoryService extends Service<MsgHistory> {

    List<MsgHistory> listMsgHistory(String fromId, String toId);

    int countNoReadHistory(String fromId, String toId);

    MsgHistory getLastHistory(String fromId, String toId);

    void clearNoReadCount(String fromId, String toId);

    @NonNull
    MsgHistoryDTO convertTo(@NonNull MsgHistory msgHistory) ;

    @NonNull
    List<MsgHistoryDTO> convertTo(@NonNull List<MsgHistory> msgHistories) ;
}
