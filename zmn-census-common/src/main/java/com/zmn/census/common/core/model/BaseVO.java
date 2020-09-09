package com.zmn.census.common.core.model;


import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Created by zhaozhirong on 2019/3/4.
 *
 * 基础实体
 */
public class BaseVO extends BaseIdVO{




    /**
     * 是否删除 0未删除，1已删除
     */
    protected Integer deleted;

    /**
     * 创建日期
     */
    protected Date createdDate;

    /**
     * 修改日期
     */
    protected Date modifiedDate;

    /**
     * 创建人id
     */
    private Integer createUserId;

    /**
     * 创建人名称
     */
    private String createUserName;

    protected String createdDateStr;

    protected String modifiedDateStr;



    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedDateStr() {
        if(this.createdDate != null){
            return DateUtil.formatDateTime(this.createdDate);
        }
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }

    public String getModifiedDateStr() {
        if(this.modifiedDate != null){
            return DateUtil.formatDateTime(this.modifiedDate);
        }
        return modifiedDateStr;
    }

    public void setModifiedDateStr(String modifiedDateStr) {
        this.modifiedDateStr = modifiedDateStr;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
