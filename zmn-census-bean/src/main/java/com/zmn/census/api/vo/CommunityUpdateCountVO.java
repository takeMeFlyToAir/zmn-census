package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseIdVO;
import lombok.Data;

/**
 * @ClassName CommunityUpdateCountVO
 * Description 更新小区的统计
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
@Data
public class CommunityUpdateCountVO extends BaseIdVO {

    private Integer addHoldCount;

    private Integer addPersonCount;

}
