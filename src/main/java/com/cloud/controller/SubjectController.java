package com.cloud.controller;

import com.cloud.bean.Message;
import com.cloud.util.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ＊转发服务的 Controller 层，用于接收数据来集发送来的数据
 */
@RestController
public class SubjectController {
    @RequestMapping("/sendData")
    public Message sendData(String appType, String dataType) {
        Message message = new Message();
        if (StringUtils.isBlank(appType) || StringUtils.isBlank(dataType)) {
            message.setCode(1000);
            message.setMessage("参数非法");
            return message;
        }
        try {
//            构建观察者 并通知观察者进行更新
            SubjectFactory.getSubject(appType, dataType).notifyObservers();
            message.setCode(1001);
            message.setMessage("数据发送成功");
        } catch (Exception e) {
            Log.error(" 数据处理异常信息为 " + e.getMessage());
            message.setCode(1002);
            message.setMessage("数据处理错误");
        }
        return message;
    }
}
