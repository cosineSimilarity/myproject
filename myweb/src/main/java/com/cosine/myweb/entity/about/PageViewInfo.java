package com.cosine.myweb.entity.about;

import java.time.LocalDateTime;

/**
 * @author Cosine
 * @version 1.0.0
 * @title PageViewInfo
 * @create 2023/6/28
 * @description
 */
public class PageViewInfo {
    private int id;
    private long pageviewCount;
    private LocalDateTime updateTime;
    private String pageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPageviewCount() {
        return pageviewCount;
    }

    public void setPageviewCount(long pageviewCount) {
        this.pageviewCount = pageviewCount;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @Override
    public String toString() {
        return "PageViewInfo{" +
                "id=" + id +
                ", pageviewCount=" + pageviewCount +
                ", updateTime=" + updateTime +
                ", pageName='" + pageName + '\'' +
                '}';
    }
}
