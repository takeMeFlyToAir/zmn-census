package com.zmn.census.action.service;

import com.zmn.census.action.api.ConfigService;
import com.zmn.census.action.entity.ConfigEntity;
import com.zmn.census.action.mapper.ConfigMapper;
import com.zmn.census.api.vo.*;
import com.zmn.census.common.core.service.BaseService;
import com.zmn.census.common.utils.object.VoAndBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName CommunityServiceImpl
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class ConfigServiceImpl extends BaseService<ConfigEntity> implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;



    @Override
    public List<ConfigVO> findList() {
        Example example = new Example(ConfigEntity.class);
        example.setOrderByClause(" created_date desc ");
        example.createCriteria().andEqualTo("deleted", 0);
        List<ConfigEntity> configEntityList = configMapper.selectByExample(example);
        return VoAndBeanUtils.toVOList(configEntityList, ConfigVO.class);
    }

    @Override
    public ConfigVO getByKey(String key) {
        Example example = new Example(ConfigEntity.class);
        example.setOrderByClause(" created_date desc ");
        example.createCriteria().andEqualTo("deleted", 0)
                .andEqualTo("key",key);
        List<ConfigEntity> configEntityList = configMapper.selectByExample(example);
        if(configEntityList.size() > 0){
            return VoAndBeanUtils.toVO(configEntityList.get(0),ConfigVO.class);
        }
        return null;
    }

    @Override
    public ConfigVO save(ConfigAddVO configAddVO) {
        ConfigEntity configEntity = VoAndBeanUtils.fromVO(configAddVO, ConfigEntity.class);
        this.save(configEntity);
        return VoAndBeanUtils.toVO(configEntity,ConfigVO.class);
    }


    @Override
    public void edit(ConfigVO configVO) {
        ConfigEntity configEntity = VoAndBeanUtils.fromVO(configVO, ConfigEntity.class);
        this.updateNotNull(configEntity);
    }
}
