package com.zmn.census.action.api;


import com.zmn.census.action.entity.ConfigEntity;
import com.zmn.census.api.vo.*;
import com.zmn.census.common.core.service.IService;

import java.util.List;

/**
 * Created by zhaozhirong on 2019/11/25.
 */
public interface ConfigService extends IService<ConfigEntity> {



    ConfigVO save(ConfigAddVO configVO);

    void edit(ConfigVO configVO);

    List<ConfigVO> findList();

    ConfigVO getByKey(String key);

}
