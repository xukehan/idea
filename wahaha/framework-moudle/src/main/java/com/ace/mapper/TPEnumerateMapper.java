package com.ace.mapper;

import com.ace.pojo.TPEnumerate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TPEnumerateMapper {
    int deleteByPrimaryKey(String attrCode);

    int insert(TPEnumerate record);

    int insertSelective(TPEnumerate record);

    TPEnumerate selectByPrimaryKey(String attrCode);

    int updateByPrimaryKeySelective(TPEnumerate record);

    int updateByPrimaryKey(TPEnumerate record);
}
