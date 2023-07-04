package com.cosine.myweb.service.about;

import com.cosine.myweb.entity.about.PageViewInfo;

import java.util.List;

/**
 * @author Cosine
 * @version 1.0.0
 * @title PageViewCountService
 * @create 2023/6/28
 * @description
 */
public interface PageViewCountService {

    String FLAG_CREATE="create";
    String FLAG_UPDATE="update";

    boolean savePageViewCount2Mysql(PageViewInfo pageViewInfo, String flag);

    PageViewInfo queryPageViewCountByPageName(String pageName);

    List<PageViewInfo> queryAllPageViewCount();

    List<String> queryAllPageViewCountKey();
}
