package com.company.project.model;

import com.company.project.common.PageParam;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_course_chapter_video")
public class CourseChapterVideo extends PageParam {
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
    private Integer status;

    /**
     * 章节ID
     */
    @Column(name = "course_chapter_id")
    private Long courseChapterId;

    /**
     * 名称
     */
    private String name;

    /**
     * 视频
     */
    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "graphic_Details")
    private String graphicDetails;

    @Column(name = "chapter_number")
    private Integer chapterNumber;

    @Transient
    private String courseChapterName;

    @Transient
    private CourseChapterVideo frontCourseChapterVideo;

    @Transient
    private CourseChapterVideo afterCourseChapterVideo;

    public CourseChapterVideo getFrontCourseChapterVideo() {
        return frontCourseChapterVideo;
    }

    public void setFrontCourseChapterVideo(CourseChapterVideo frontCourseChapterVideo) {
        this.frontCourseChapterVideo = frontCourseChapterVideo;
    }

    public CourseChapterVideo getAfterCourseChapterVideo() {
        return afterCourseChapterVideo;
    }

    public void setAfterCourseChapterVideo(CourseChapterVideo afterCourseChapterVideo) {
        this.afterCourseChapterVideo = afterCourseChapterVideo;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getCourseChapterName() {
        return courseChapterName;
    }

    public void setCourseChapterName(String courseChapterName) {
        this.courseChapterName = courseChapterName;
    }

    public String getGraphicDetails() {
        return graphicDetails;
    }

    public void setGraphicDetails(String graphicDetails) {
        this.graphicDetails = graphicDetails;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态
     *
     * @param status 业务状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取章节ID
     *
     * @return course_chapter_id - 章节ID
     */
    public Long getCourseChapterId() {
        return courseChapterId;
    }

    /**
     * 设置章节ID
     *
     * @param courseChapterId 章节ID
     */
    public void setCourseChapterId(Long courseChapterId) {
        this.courseChapterId = courseChapterId;
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