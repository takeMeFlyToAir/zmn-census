package com.zmn.census.api.service;

import com.zmn.census.api.vo.CommunityVO;

import java.util.List;

/**
 * @ClassName CommunityService
 * Description 小区
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
public interface CommunityService {


    List<CommunityVO> findList();

}
