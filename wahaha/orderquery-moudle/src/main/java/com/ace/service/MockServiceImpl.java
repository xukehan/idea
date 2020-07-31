package com.ace.service;

import com.ace.inter.ImockService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Slf4j
@RequestMapping(value = "/mockService")
@Api(value = "mockService|订单查询服务")
@Controller
public class MockServiceImpl implements ImockService {

    @Override
    @RequestMapping(value = "/woys/invoke/**",method = RequestMethod.POST)
    @ResponseBody
    public String invoke(@RequestBody String param) {
        log.info("----调woys的mock方法");
        JSONObject json = JSON.parseObject(param);
        String method = json.getString("method");
        log.info("---method={}",method);

        //此处采用数据库配置
        String jsonFileName = "";

        log.info("调用的服务method={}，读取的文件为{}",method,jsonFileName);

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jsonfiles/"+ jsonFileName + ".json");
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException:"+e);
        } catch (IOException e) {
            log.error("IOException:"+e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("close br error:" + e);
                }
            }
        }

        String result = sb.toString();
        log.info("读取到的内容："+result);

        return result;
    }
}
