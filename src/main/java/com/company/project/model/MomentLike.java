package com.company.project.model;

import com.company.project.common.PageParam;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_moment_like")
public class MomentLike extends PageParam {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * 是否删除(0false未删除 1true已删除)
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 版本号
     */
    private Integer version;

    private Integer status;

    /**
     * 点赞主体id（文章id或者评论id）
     */
    @Column(name = "subject_id")
    private Long subjectId;

    /**
     * 点赞类型 1-文章 2-评论
     */
    private Integer type;

    /**
     * 状态：
     */
    private Integer state;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取修改人
     *
     * @return updated_by - 修改人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置修改人
     *
     * @param updatedBy 修改人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置更新时间
     *
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取是否删除(0false未删除 1true已删除)
     *
     * @return is_delete - 是否删除(0false未删除 1true已删除)
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除(0false未删除 1true已删除)
     *
     * @param isDelete 是否删除(0false未删除 1true已删除)
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取点赞主体id（文章id或者评论id）
     *
     * @return subject_id - 点赞主体id（文章id或者评论id）
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * 设置点赞主体id（文章id或者评论id）
     *
     * @param subjectId 点赞主体id（文章id或者评论id）
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取点赞类型 1-文章 2-评论
     *
     * @return type - 点赞类型 1-文章 2-评论
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置点赞类型 1-文章 2-评论
     *
     * @param type 点赞类型 1-文章 2-评论
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取状态：
     *
     * @return state - 状态：
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态：
     *
     * @param state 状态：
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
