package com.company.project.model;

import com.company.project.common.PageParam;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_collection_list")
public class CollectionList extends PageParam {
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
     * 播放次数
     */
    private Integer yearnum;

    /**
     * 名称
     */
    private String name;

    /**
     * 简介
     */
    @Column(name = "graphic_details")
    private String graphicDetails;

    /**
     * 封面
     */
    @Column(name = "img_url")
    private String imgUrl;

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
     * 获取播放次数
     *
     * @return yearnum - 播放次数
     */
    public Integer getYearnum() {
        return yearnum;
    }

    /**
     * 设置播放次数
     *
     * @param yearnum 播放次数
     */
    public void setYearnum(Integer yearnum) {
        this.yearnum = yearnum;
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
     * 获取简介
     *
     * @return graphic_details - 简介
     */
    public String getGraphicDetails() {
        return graphicDetails;
    }

    /**
     * 设置简介
     *
     * @param graphicDetails 简介
     */
    public void setGraphicDetails(String graphicDetails) {
        this.graphicDetails = graphicDetails;
    }

    /**
     * 获取封面
     *
     * @return img_url - 封面
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置封面
     *
     * @param imgUrl 封面
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}