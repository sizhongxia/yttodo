package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "td_user_wx")
public class TdUserWx {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 记录ID
     */
    @Column(name = "res_id")
    private String resId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 微信APPID
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 微信OPENID
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 微信UnionId
     */
    @Column(name = "union_id")
    private String unionId;

    /**
     * 城市名称
     */
    @Column(name = "session_key")
    private String sessionKey;

    /**
     * 记录版本
     */
    private Integer version;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 最后修改时间
     */
    @Column(name = "update_at")
    private Date updateAt;

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
     * 获取记录ID
     *
     * @return res_id - 记录ID
     */
    public String getResId() {
        return resId;
    }

    /**
     * 设置记录ID
     *
     * @param resId 记录ID
     */
    public void setResId(String resId) {
        this.resId = resId;
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
     * 获取微信APPID
     *
     * @return app_id - 微信APPID
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置微信APPID
     *
     * @param appId 微信APPID
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 获取微信OPENID
     *
     * @return open_id - 微信OPENID
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信OPENID
     *
     * @param openId 微信OPENID
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取微信UnionId
     *
     * @return union_id - 微信UnionId
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 设置微信UnionId
     *
     * @param unionId 微信UnionId
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * 获取城市名称
     *
     * @return session_key - 城市名称
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * 设置城市名称
     *
     * @param sessionKey 城市名称
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * 获取记录版本
     *
     * @return version - 记录版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置记录版本
     *
     * @param version 记录版本
     */
    public void setVersion(Integer version) {
        this.version = version;
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
     * 获取最后修改时间
     *
     * @return update_at - 最后修改时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateAt 最后修改时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}