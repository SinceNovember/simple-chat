package com.simple.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simple.model.dto.base.OutputConverter;
import com.simple.model.entity.MsgHistory;
import com.simple.model.enums.MsgReadType;
import lombok.Data;

import java.util.Date;

/**
 * 博客DTO,继承了DTO从entity那获取数据
 */
@Data
public class MsgHistoryDTO implements OutputConverter<MsgHistoryDTO, MsgHistory> {

    private String rowguid;

    private String fromId;

    private String fromName;

    private String toId;

    private String toName;

    private String content;

    private MsgReadType isRead;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createTime;

}
