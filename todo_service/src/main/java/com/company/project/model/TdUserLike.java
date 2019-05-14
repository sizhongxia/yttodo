package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "td_user_like")
public class TdUserLike {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 最后修改时间
     */
    @Column(name = "click_at")
    private Date clickAt;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取最后修改时间
     *
     * @return click_at - 最后修改时间
     */
    public Date getClickAt() {
        return clickAt;
    }

    /**
     * 设置最后修改时间
     *
     * @param clickAt 最后修改时间
     */
    public void setClickAt(Date clickAt) {
        this.clickAt = clickAt;
    }
}