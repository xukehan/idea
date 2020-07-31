package com.ace.mapper;

import com.ace.pojo.TbOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    TbOrder selectByPrimaryKey(Long orderId);

    List <TbOrder> selectAll();

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);

    List selectId(String paymentType);
}
