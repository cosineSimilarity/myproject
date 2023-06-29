package com.cosine.myweb.service.about.impl;

import com.cosine.myweb.dao.about.AboutMapper;
import com.cosine.myweb.entity.about.PageViewInfo;
import com.cosine.myweb.service.about.PageViewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cosine
 * @version 1.0.0
 * @title PageViewCountServiceImpl
 * @create 2023/6/28
 * @description 保存页面访问量到msql数据库
 */
@Service
public class PageViewCountServiceImpl implements PageViewCountService {

    @Autowired
    AboutMapper aboutMapper;

    @Override
    public boolean savePageViewCount2Mysql(PageViewInfo pageViewInfo, String flag) {
        if("create".equals(flag)){
            return aboutMapper.createPageViewCount2Mysql(pageViewInfo);
        }else {
            return aboutMapper.updatePageViewCount2Mysql(pageViewInfo);
        }
    }

    @Override
    public PageViewInfo queryPageViewCountByPageName(String pageName) {
        return aboutMapper.queryPageViewCountByPageName(pageName);
    }
}
