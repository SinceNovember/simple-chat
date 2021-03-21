package com.simple.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.simple.model.dto.MsgHistoryDTO;
import com.simple.model.entity.MsgHistory;
import com.simple.model.entity.User;
import com.simple.service.MsgHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/msg")
public class MsgHistoryController {
    @Resource
    private MsgHistoryService msgHistoryService;

    @GetMapping("/history")
    public ResponseEntity<List<MsgHistoryDTO>> msgHistory(@SessionAttribute("user") User user, String toId){
        List<MsgHistory> msgHistories = msgHistoryService.listMsgHistory(user.getUserId(), toId);
        return ResponseEntity.ok(msgHistoryService.convertTo(msgHistories));
    }

    /**
     * 清除未读消息数
     * @param user
     * @param toId
     * @return
     */
    @PutMapping("/clear")
    public ResponseEntity<User> clearNoReadCount(@SessionAttribute("user") User user, @NonNull @RequestBody String param) {
        String toId = JSONObject.parseObject(param).getString("toId");
        if (StringUtils.isEmpty(toId)) {
            log.error("发送者Id不能为空");
            return null;
        }
        msgHistoryService.clearNoReadCount(toId, user.getUserId());
        return ResponseEntity.ok(user);
    }

}
