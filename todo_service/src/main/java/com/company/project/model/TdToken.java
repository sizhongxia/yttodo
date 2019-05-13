package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "td_token")
public class TdToken {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户Token
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 来源类型（PCA, MP）
     */
    @Column(name = "source_type")
    private String sourceType;

    private String token;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 最后访问时间
     */
    @Column(name = "last_visit_at")
    private Date lastVisitAt;

    /**
     * 过期时间
     */
    @Column(name = "overdue_at")
    private Date overdueAt;

    /**
     * 是否被禁止
     */
    @Column(name = "is_forbidden")
    private Integer isForbidden;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户Token
     *
     * @return user_id - 用户Token
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户Token
     *
     * @param userId 用户Token
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取来源类型（PCA, MP）
     *
     * @return source_type - 来源类型（PCA, MP）
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 设置来源类型（PCA, MP）
     *
     * @param sourceType 来源类型（PCA, MP）
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取最后访问时间
     *
     * @return last_visit_at - 最后访问时间
     */
    public Date getLastVisitAt() {
        return lastVisitAt;
    }

    /**
     * 设置最后访问时间
     *
     * @param lastVisitAt 最后访问时间
     */
    public void setLastVisitAt(Date lastVisitAt) {
        this.lastVisitAt = lastVisitAt;
    }

    /**
     * 获取过期时间
     *
     * @return overdue_at - 过期时间
     */
    public Date getOverdueAt() {
        return overdueAt;
    }

    /**
     * 设置过期时间
     *
     * @param overdueAt 过期时间
     */
    public void setOverdueAt(Date overdueAt) {
        this.overdueAt = overdueAt;
    }

    /**
     * 获取是否被禁止
     *
     * @return is_forbidden - 是否被禁止
     */
    public Integer getIsForbidden() {
        return isForbidden;
    }

    /**
     * 设置是否被禁止
     *
     * @param isForbidden 是否被禁止
     */
    public void setIsForbidden(Integer isForbidden) {
        this.isForbidden = isForbidden;
    }
}