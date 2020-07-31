package com.ace.service;

import com.ace.busi.TbOrderBusi;
import com.ace.mapper.TbOrderMapper;
import com.ace.pojo.TbOrder;
import com.ace.inter.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: xukh
 * @Date: 2019/11/15 11:32
 * @Description:
 */
@Slf4j
@RequestMapping(value = "/orderService")
@Api(value = "AbOrderService|订单查询服务")
@Controller
public class AbOrderService implements IOrderService {

    @Autowired
    private TbOrderBusi orderBusi;

    @Autowired
    private TbOrderMapper orderMapper;


    @Override
    @ResponseBody
    @RequestMapping(value = "/queryOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "查询订单列表", notes ="xukh")
    public List<TbOrder> queryOrderList() {
        return  orderBusi.queryList();
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
    @ApiOperation(value = "查询订单列表", notes ="xukh")
    public TbOrder queryOrder(@RequestBody TbOrder param) throws Exception {
        log.info("入参==========="+param.toString());
        Long orderId = param.getOrderId();
        if (StringUtils.isEmpty(orderId)){
            throw new Exception("orderId为空");
        }
        TbOrder tbOrder = orderBusi.queryOrder(orderId);
        log.info(tbOrder.toString());
        if (StringUtils.isEmpty(tbOrder)){
            throw new Exception("未查询到此订单");
        }

        return tbOrder;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/queryOrderId", method = RequestMethod.POST)
    @ApiOperation(value = "查询订单列表", notes ="xukh")
    public List queryOrderId(@RequestBody TbOrder param) {

        String paymentType = param.getPaymentType();
        List list = orderMapper.selectId(paymentType);
        System.out.println(list);
        return list;
    }

    @Override
    @Scheduled(cron = "*/5 * * * * ?")
    public void quart() {
        System.out.println("执行定时任务");
    }
}
