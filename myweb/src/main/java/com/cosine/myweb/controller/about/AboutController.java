package com.cosine.myweb.controller.about;

import com.cosine.myweb.common.ResModel;
import com.cosine.myweb.common.StatusCode;
import com.cosine.myweb.entity.about.PageViewInfo;
import com.cosine.myweb.service.about.PageViewCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *@title AboutController
 *@author Cosine
 *@create 2023/6/28
 *@version 1.0.0
 *@description about页面控制器
 */
@RestController
@RequestMapping("/about")
@Slf4j
public class AboutController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private PageViewCountService pageViewCountService;

    @RequestMapping("/pageview/count")
    public String pageViewCount(@RequestParam String pageName){
        ResModel<Long> resModel = new ResModel<>();
        //判断页面名参数是否为空
        if(pageName==null || pageName.isEmpty()){
            resModel.setStatusCode(StatusCode.NOT_FOUND_CODE);
            resModel.setMessage("pageName为空");
            return resModel.toJSONString();
        }

        //从redis中获取访问量
        String keyName = pageName+":pageViewCount";
        String pageViewCountStr = redisTemplate.opsForValue().get(keyName);
        long pageViewCount;
        if(pageViewCountStr!=null){
            pageViewCount = Long.parseLong(pageViewCountStr);
            redisTemplate.opsForValue().increment(keyName);
            log.info("Redis获取页面{}访问量成功，{}：{}",pageName,keyName,pageViewCount+1);
        }else {
            log.info("Redis获取页面{}访问量失败，尝试从Mysql中获取",pageName);
            PageViewInfo pageViewInfo = pageViewCountService.queryPageViewCountByPageName(pageName);
            if(pageViewInfo!=null){
                pageViewCount=pageViewInfo.getPageviewCount();
                redisTemplate.opsForValue().set(keyName, String.valueOf(pageViewCount+1));
                log.info("Mysql获取页面{}访问量成功，将数据传入Redis，{}：{}",pageName,keyName,pageViewCount+1);
            }else {
                pageViewCount = 0;
                pageViewInfo = new PageViewInfo();
                pageViewInfo.setPageName(pageName);
                pageViewInfo.setPageviewCount(pageViewCount+1);
                boolean isSave= pageViewCountService.savePageViewCount2Mysql(pageViewInfo, PageViewCountService.FLAG_CREATE);
                if(!isSave){
                    resModel.setStatusCode(StatusCode.FAIL_CODE);
                    resModel.setMessage("Mysql初始化页面"+pageName+"访问量失败");
                    return resModel.toJSONString();
                }
                redisTemplate.opsForValue().increment(keyName);
                log.info("Mysql获取页面{}访问量失败,初始化访问量{}：{}",pageName,keyName,pageViewCount+1);
            }

        }

        resModel.setStatusCode(StatusCode.SUCCESS_CODE);
        resModel.setResultData(++pageViewCount);
        return resModel.toJSONString();
    }
}
