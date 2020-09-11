package com.zmn.census.action.service;

import com.zmn.census.action.api.PersonInfoService;
import com.zmn.census.action.entity.PersonInfoEntity;
import com.zmn.census.action.mapper.PersonInfoMapper;
import com.zmn.census.common.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PersonInfoServiceImpl
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class PersonInfoServiceImpl extends BaseService<PersonInfoEntity> implements PersonInfoService {

    @Autowired
    private PersonInfoMapper personInfoMapper;

}
