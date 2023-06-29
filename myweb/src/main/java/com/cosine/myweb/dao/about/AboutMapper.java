package com.cosine.myweb.dao.about;

import com.cosine.myweb.entity.about.PageViewInfo;
import org.springframework.stereotype.Repository;

/**
 * @author Cosine
 * @version 1.0.0
 * @title AboutMapper
 * @create 2023/6/28
 * @description
 */
@Repository
public interface AboutMapper {

    /**
     * @author Cosine
     * @param[pageViewCount]
     * @return boolean
     * @time 2023/6/28 23:43
     * @description 创建页面访问量
     */
    boolean createPageViewCount2Mysql(PageViewInfo pageViewInfo);

    /**
     * @author Cosine
     * @param[pageViewInfo]
     * @return boolean
     * @time 2023/6/28 23:46
     * @description 更新页面访问量
     */
    boolean updatePageViewCount2Mysql(PageViewInfo pageViewInfo);

    /**
     * @author Cosine
     * @param[pageName]
     * @return PageViewInfo
     * @time 2023/6/28 23:43
     * @description 通过页面名称查找页面访问量
     */
    PageViewInfo queryPageViewCountByPageName(String pageName);
}
