package com.zmn.census.common.utils.object;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaozhirong on 2019/11/27.
 */
public class VoAndBeanUtils {

    public static  List fromVOList(List sourceList,Class clazz){
        return toList(sourceList,clazz);
    }

    public static  List toVOList(List sourceList,Class clazz){
        return toList(sourceList,clazz);
    }

    public static <T> T  fromVO(Object source,Class<T> clazz){
       return to(source, clazz);
    }

    public static <T> T  toVO(Object source,Class<T> clazz){
        return to(source, clazz);
    }

    private static <T> T  to(Object source,Class<T> clazz){
        if(ObjectUtil.isNull(source)){
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(source), clazz);
    }

    private static  List toList(List sourceList,Class clazz){
        if(CollUtil.isEmpty(sourceList)){
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(sourceList), clazz);
    }

}
