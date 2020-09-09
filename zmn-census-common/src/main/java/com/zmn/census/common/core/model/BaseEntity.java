package com.zmn.census.common.core.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 * 基础实体
 */
public class BaseEntity {


    /**
     * 数据源ID
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "JDBC")
    protected Integer id;

    /**
     * 是否删除 0未删除，1已删除
     */
    protected Integer deleted;

    /**
     * 创建日期
     */
    private Date createdDate;

    /**
     * 修改日期
     */
    private Date modifiedDate;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
