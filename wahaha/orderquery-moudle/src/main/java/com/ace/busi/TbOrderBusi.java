package com.ace.busi;

import com.ace.mapper.TbOrderMapper;
import com.ace.pojo.TbOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: xukh
 * @Date: 2019/11/15 14:28
 * @Description:
 */
@Component
public class TbOrderBusi {

    @Autowired
    private TbOrderMapper orderMapper;

    public List<TbOrder> queryList(){
       return orderMapper.selectAll();


    }

    public TbOrder queryOrder(Long orderId){
       return orderMapper.selectByPrimaryKey(orderId);
    }


         dfdsf

    private sisodfs   ;


    private sdfskdfhskdjfh;

    sdfsdfsfdsfsfdsfsdf

}
