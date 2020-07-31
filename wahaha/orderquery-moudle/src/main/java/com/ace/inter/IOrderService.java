package com.ace.inter;

import com.ace.pojo.TbOrder;

import java.util.List;

/**
 * @Auther: xukh
 * @Date: 2019/11/15 11:01
 * @Description:
 */
public interface IOrderService {

    public List<TbOrder> queryOrderList();

    public  TbOrder queryOrder(TbOrder param) throws Exception;


    public  List queryOrderId(TbOrder param);

    public void quart();



}
