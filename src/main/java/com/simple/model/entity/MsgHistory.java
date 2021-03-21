package com.simple.model.entity;

import com.simple.model.enums.MsgReadType;
import com.simple.model.enums.MsgType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Table(name = "msg_history")
public class MsgHistory {

    @Id
    @Column(name = "rowguid")
    private String rowguid;

    @Column(name = "fromId")
    private String fromId;

    private String fromName;

    @Column(name = "toId")
    private String toId;

    private String toName;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "type")
    private MsgType type;

    @Column(name = "is_read")
    private MsgReadType isRead;


}
