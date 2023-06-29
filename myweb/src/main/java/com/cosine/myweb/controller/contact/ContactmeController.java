package com.cosine.myweb.controller.contact;

import com.cosine.myweb.entity.contact.ContactmeInfo;
import com.cosine.myweb.service.contact.ContactmeService;
import com.cosine.myweb.common.ResModel;
import com.cosine.myweb.common.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@title ContactmeController
 *@author Cosine
 *@create 2023/6/28
 *@version 1.0.0
 *@description 联系我页面控制器
 */
@RestController
@Slf4j
public class ContactmeController {

    @Autowired
    private ContactmeService contactmeService;

    @RequestMapping("/contactme/add")
    public String contactMe(@RequestBody ContactmeInfo contactmeInfo){
        ResModel<String> resModel = new ResModel<>();
        log.info("联系我页面表单提交的数据：{}：",contactmeInfo.toString());
        try{
            int count = contactmeService.addContactMeInfo(contactmeInfo);
            log.info("新增联系我信息成功，新增数量：{}",count);
        }catch(Exception e) {
            log.error("新增联系我信息报错，错误原因：{}",e.getMessage());
            resModel.setStatusCode(StatusCode.FAIL_CODE);
            return resModel.toJSONString();
        }
        resModel.setStatusCode(StatusCode.SUCCESS_CODE);
        return resModel.toJSONString();
    }
}
