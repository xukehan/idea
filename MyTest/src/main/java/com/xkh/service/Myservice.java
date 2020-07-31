package com.xkh.service;


import com.xkh.mapper.CstCustomerMapper;
import com.xkh.pojo.CstCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

/**
 * @Auther: xukh
 * @Date: 2019/10/11 14:59
 * @Description:
 */
@Service
public class Myservice {

    @Autowired
    private CstCustomerMapper customerMapper;

    public CstCustomer queryName(Long custId){

       return customerMapper.selectByPrimaryKey(custId);

    }


    @Transactional
    public void updateCustomer(){

        CstCustomer cstCustomer = new CstCustomer();
        cstCustomer.setCustId(Long.valueOf("1"));
        cstCustomer.setCustName("呆逼");
        customerMapper.updateByPrimaryKeySelective(cstCustomer);
       // int i =1/0;
        cstCustomer.setCustAddress("新地中心");
        customerMapper.updateByPrimaryKeySelective(cstCustomer);

    }

}
