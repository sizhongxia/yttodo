package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "td_plan")
public class TdPlan {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户标识
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 计划ID
     */
    @Column(name = "plan_id")
    private String planId;

    /**
     * 计划内容
     */
    @Column(name = "plan_content")
    private String planContent;

    /**
     * 重要程度
     */
    @Column(name = "importance_level")
    private Integer importanceLevel;

    /**
     * 紧急程度
     */
    @Column(name = "urgent_level")
    private Integer urgentLevel;

    /**
     * 状态（R:进行中，Y:已完成，N:已取消，D:已删除）
     */
    @Column(name = "plan_status")
    private String planStatus;

    /**
     * 到期时间
     */
    @Column(name = "due_at")
    private Date dueAt;

    /**
     * 小程序FormID
     */
    @Column(name = "form_id")
    private String formId;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
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
     * 获取用户标识
     *
     * @return user_id - 用户标识
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户标识
     *
     * @param userId 用户标识
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取计划ID
     *
     * @return plan_id - 计划ID
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * 设置计划ID
     *
     * @param planId 计划ID
     */
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * 获取计划内容
     *
     * @return plan_content - 计划内容
     */
    public String getPlanContent() {
        return planContent;
    }

    /**
     * 设置计划内容
     *
     * @param planContent 计划内容
     */
    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    /**
     * 获取重要程度
     *
     * @return importance_level - 重要程度
     */
    public Integer getImportanceLevel() {
        return importanceLevel;
    }

    /**
     * 设置重要程度
     *
     * @param importanceLevel 重要程度
     */
    public void setImportanceLevel(Integer importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    /**
     * 获取紧急程度
     *
     * @return urgent_level - 紧急程度
     */
    public Integer getUrgentLevel() {
        return urgentLevel;
    }

    /**
     * 设置紧急程度
     *
     * @param urgentLevel 紧急程度
     */
    public void setUrgentLevel(Integer urgentLevel) {
        this.urgentLevel = urgentLevel;
    }

    /**
     * 获取状态（R:进行中，Y:已完成，N:已取消，D:已删除）
     *
     * @return plan_status - 状态（R:进行中，Y:已完成，N:已取消，D:已删除）
     */
    public String getPlanStatus() {
        return planStatus;
    }

    /**
     * 设置状态（R:进行中，Y:已完成，N:已取消，D:已删除）
     *
     * @param planStatus 状态（R:进行中，Y:已完成，N:已取消，D:已删除）
     */
    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    /**
     * 获取到期时间
     *
     * @return due_at - 到期时间
     */
    public Date getDueAt() {
        return dueAt;
    }

    /**
     * 设置到期时间
     *
     * @param dueAt 到期时间
     */
    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }

    /**
     * 获取小程序FormID
     *
     * @return form_id - 小程序FormID
     */
    public String getFormId() {
        return formId;
    }

    /**
     * 设置小程序FormID
     *
     * @param formId 小程序FormID
     */
    public void setFormId(String formId) {
        this.formId = formId;
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
     * 获取更新时间
     *
     * @return update_at - 更新时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置更新时间
     *
     * @param updateAt 更新时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}