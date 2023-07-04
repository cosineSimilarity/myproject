package com.cosine.myweb.util;

import com.cosine.myweb.entity.about.PageViewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cosine
 * @version 1.0.0
 * @title ListUtil
 * @create 2023/7/5
 * @description List相关工具
 */
public class ListUtil {

    public static <T>List<? extends T> obj2List(Object obj,Class<T> clazz){
        List<T> list = new ArrayList<>();
        if(obj instanceof List<?>){
            for(Object o : (List<?>)obj){
                if(clazz.isInstance(o)){
                    list.add(clazz.cast(o));
                }
            }
            return list;
        }
        return null;
    }
}
