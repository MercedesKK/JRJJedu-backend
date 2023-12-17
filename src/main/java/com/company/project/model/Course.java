package com.company.project.model;

import com.company.project.common.PageParam;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "t_course")
public class Course extends PageParam {
    /**
     * 记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 是否删除(0false未删除 1true已删除)
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 业务状态
     */
    private Long status;

    /**
     * 分类ID
     */
    @Column(name = "course_type_id")
    private Long courseTypeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 图文详情
     */
    @Column(name = "graphic_details")
    private String graphicDetails;

    /**
     * 图片
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 视频
     */
    @Column(name = "video_url")
    private String videoUrl;

    private Integer views;

    private BigDecimal price;

    @Transient
    private String courseTypeName;

    @Transient
    private String goodsName;

    @Transient
    private List<MomentComment> momentCommentList;

    @Transient
    private String userName;

    @Transient
    private String userDesc;

    @Transient
    private String avatar;

    @Transient
    private List<CourseChapter> courseChapterList;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public List<CourseChapter> getCourseChapterList() {
        return courseChapterList;
    }

    public void setCourseChapterList(List<CourseChapter> courseChapterList) {
        this.courseChapterList = courseChapterList;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<MomentComment> getMomentCommentList() {
        return momentCommentList;
    }

    public void setMomentCommentList(List<MomentComment> momentCommentList) {
        this.momentCommentList = momentCommentList;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    /**
     * 获取记录ID
     *
     * @return id - 记录ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置记录ID
     *
     * @param id 记录ID
     */
    public void setId(Long id) {
        this.id = id;
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
     * 获取业务状态
     *
     * @return status - 业务状态
     */
    public Long getStatus() {
        return status;
    }

    /**
     * 设置业务状态
     *
     * @param status 业务状态
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * 获取分类ID
     *
     * @return course_type_id - 分类ID
     */
    public Long getCourseTypeId() {
        return courseTypeId;
    }

    /**
     * 设置分类ID
     *
     * @param courseTypeId 分类ID
     */
    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取图文详情
     *
     * @return graphic_details - 图文详情
     */
    public String getGraphicDetails() {
        return graphicDetails;
    }

    /**
     * 设置图文详情
     *
     * @param graphicDetails 图文详情
     */
    public void setGraphicDetails(String graphicDetails) {
        this.graphicDetails = graphicDetails;
    }

    /**
     * 获取图片
     *
     * @return img_url - 图片
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片
     *
     * @param imgUrl 图片
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取视频
     *
     * @return video_url - 视频
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 设置视频
     *
     * @param videoUrl 视频
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}