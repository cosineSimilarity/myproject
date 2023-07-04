package com.cosine.myweb.scheduler;

import com.alibaba.fastjson2.JSON;
import com.cosine.myweb.dao.about.AboutMapper;
import com.cosine.myweb.entity.about.PageViewInfo;
import com.cosine.myweb.service.about.PageViewCountService;
import com.cosine.myweb.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cosine
 * @version 1.0.0
 * @title SyncRedis2MysqlScheduler
 * @create 2023/7/4
 * @description 同步redis数据到mysql
 */
@Component
@Slf4j
public class SyncRedis2MysqlScheduler {

    @Autowired
    private PageViewCountService pageViewCountService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * @author Cosine
     * @param[]
     * @time 2023/7/5 4:46
     * @description
     */
    @Scheduled(cron = "0 0 0,6,12,18 * * ?")
    public void syncPageViewCount2Mysql(){
        log.info("定时任务：同步页面访问量到mysql，任务开始！");
        //查询mysql获取所有页面访问量的key
        List<String> keyList = pageViewCountService.queryAllPageViewCountKey();
        log.info("mysql中获取的redis_key数据：{}",JSON.toJSONString(keyList));
        //根据key从redis中取出所有访问量数据
        Object resutl = redisTemplate.executePipelined((RedisCallback<Object>) connection->{
            StringRedisConnection stringRedisConnection = new DefaultStringRedisConnection(connection);
            for(String key:keyList){
                stringRedisConnection.get(key);
            }
            return null;
        });
        //将result转换为list类型
        List<? extends String> pageViewCountList = ListUtil.obj2List(resutl,String.class);
        log.info("redis中获取的页面访问量value值：{}", JSON.toJSONString(pageViewCountList));
        //组装访问量数据
        List<PageViewInfo> pageViewInfoList = new ArrayList<>();
        for(int i=0;i<keyList.size();i++){
            PageViewInfo pageViewInfo = new PageViewInfo();
            pageViewInfo.setRedisKey(keyList.get(i));
            pageViewInfo.setPageviewCount(Long.parseLong(pageViewCountList.get(i)));
            pageViewInfoList.add(pageViewInfo);
        }
        log.info("redis中获取的页面访问量数据组装完成：{}", JSON.toJSONString(pageViewInfoList));
        //批量写入数据库
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        AboutMapper aboutMapper = session.getMapper(AboutMapper.class);
        try{
            if(pageViewInfoList.size()>0){
                for(PageViewInfo pageViewInfo:pageViewInfoList){
                    aboutMapper.updatePageViewCount2Mysql(pageViewInfo);
                }
                session.commit();
                log.info("同步页面访问量到mysql成功");
            }else {
                log.error("定时任务：同步页面访问量到mysql，任务失败！");
                return;
            }
        }finally {
            session.close();
        }
        log.info("定时任务：同步页面访问量到mysql，任务结束！");
    }
}
