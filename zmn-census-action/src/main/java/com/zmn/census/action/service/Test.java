package com.zmn.census.action.service;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        List<Integer> sub = CollUtil.sub(integers, 0, 2);
        System.out.println(sub.size());
        System.out.println(sub.get(0));
        System.out.println(sub.get(1));
    }
}
