package com.simple.model.enums.converter;

import com.simple.model.enums.MsgReadType;
import com.simple.model.enums.MsgType;
import com.simple.model.enums.ValueEnum;
import org.apache.ibatis.type.MappedTypes;

/**
 * 枚举转换器，查询/保存到数据库时调用
 * @param <E>
 */
@MappedTypes({MsgType.class, MsgReadType.class})
public class EnumConverter<E extends Enum<E> & ValueEnum> extends AbstractEnumConverter<E> {
    public EnumConverter(Class<E> type) {
        super(type);
    }
}
