package com.company.project.model;

import com.company.project.common.PageParam;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "t_order")
public class Order extends PageParam {
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
     * 业务状态(0待付款;1已完成;2售后中;3已退款)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 地址
     */
    private String address;

    /**
     * 收货电话
     */
    private String phone;

    /**
     * 省ID
     */
    @Column(name = "province_id")
    private Long provinceId;

    /**
     * 省名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 市ID
     */
    @Column(name = "city_id")
    private Long cityId;

    /**
     * 市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 区ID
     */
    @Column(name = "region_id")
    private Long regionId;

    /**
     * 区名称
     */
    @Column(name = "region_name")
    private String regionName;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 状态(1归还)
     */
    private Integer state;

    /**
     * 保证金
     */
    @Column(name = "earnest_money")
    private BigDecimal earnestMoney;

    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * 收货时间
     */
    @Column(name = "receiving_time")
    private Date receivingTime;

    /**
     * 总金额
     */
    @Column(name = "total_money")
    private BigDecimal totalMoney;

    /**
     * 商家物流单号
     */
    @Column(name = "merchant_tracking_number")
    private String merchantTrackingNumber;

    /**
     * 会员物流单号
     */
    @Column(name = "member_tracking_number")
    private String memberTrackingNumber;

    @Column(name = "row_piece_id")
    private Long rowPieceId;

    @Column(name = "discount_coupon_price")
    private BigDecimal discountCouponPrice;

    @Transient
    private String goodsName;

    @Transient
    private User user;

    @Transient
    List<OrderDetails> orderDetailsList;

    @Transient
    private List<MomentComment> momentCommentList;

    @Transient
    private Long goodsId;

    @Transient
    private String imgUrl;

    @Transient
    private String grade;

    @Transient
    private String alternateName;

    @Transient
    private String language;

    @Transient
    private String goodsTypeName;

    @Transient
    private String footage;

    @Transient
    private String scriptwriter;

    @Transient
    private String jadeLaroche;

    @Transient
    private Long discountCouponId;


    public BigDecimal getDiscountCouponPrice() {
        return discountCouponPrice;
    }

    public void setDiscountCouponPrice(BigDecimal discountCouponPrice) {
        this.discountCouponPrice = discountCouponPrice;
    }

    public Long getDiscountCouponId() {
        return discountCouponId;
    }

    public void setDiscountCouponId(Long discountCouponId) {
        this.discountCouponId = discountCouponId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getFootage() {
        return footage;
    }

    public void setFootage(String footage) {
        this.footage = footage;
    }

    public String getScriptwriter() {
        return scriptwriter;
    }

    public void setScriptwriter(String scriptwriter) {
        this.scriptwriter = scriptwriter;
    }

    public String getJadeLaroche() {
        return jadeLaroche;
    }

    public void setJadeLaroche(String jadeLaroche) {
        this.jadeLaroche = jadeLaroche;
    }

    public Long getRowPieceId() {
        return rowPieceId;
    }

    public void setRowPieceId(Long rowPieceId) {
        this.rowPieceId = rowPieceId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMerchantTrackingNumber() {
        return merchantTrackingNumber;
    }

    public void setMerchantTrackingNumber(String merchantTrackingNumber) {
        this.merchantTrackingNumber = merchantTrackingNumber;
    }

    public String getMemberTrackingNumber() {
        return memberTrackingNumber;
    }

    public void setMemberTrackingNumber(String memberTrackingNumber) {
        this.memberTrackingNumber = memberTrackingNumber;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public BigDecimal getEarnestMoney() {
        return earnestMoney;
    }

    public void setEarnestMoney(BigDecimal earnestMoney) {
        this.earnestMoney = earnestMoney;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public List<MomentComment> getMomentCommentList() {
        return momentCommentList;
    }

    public void setMomentCommentList(List<MomentComment> momentCommentList) {
        this.momentCommentList = momentCommentList;
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
     * 获取业务状态(1待付款;2已付款)
     *
     * @return status - 业务状态(1待付款;2已付款)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态(1待付款;2已付款)
     *
     * @param status 业务状态(1待付款;2已付款)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取收货电话
     *
     * @return phone - 收货电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置收货电话
     *
     * @param phone 收货电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取省ID
     *
     * @return province_id - 省ID
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省ID
     *
     * @param provinceId 省ID
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取省名称
     *
     * @return province_name - 省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省名称
     *
     * @param provinceName 省名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取市ID
     *
     * @return city_id - 市ID
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * 设置市ID
     *
     * @param cityId 市ID
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取市名称
     *
     * @return city_name - 市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置市名称
     *
     * @param cityName 市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取区ID
     *
     * @return region_id - 区ID
     */
    public Long getRegionId() {
        return regionId;
    }

    /**
     * 设置区ID
     *
     * @param regionId 区ID
     */
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    /**
     * 获取区名称
     *
     * @return region_name - 区名称
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * 设置区名称
     *
     * @param regionName 区名称
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * 获取联系人
     *
     * @return linkman - 联系人
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * 设置联系人
     *
     * @param linkman 联系人
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    /**
     * 获取状态(1归还)
     *
     * @return state - 状态(1归还)
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态(1归还)
     *
     * @param state 状态(1归还)
     */
    public void setState(Integer state) {
        this.state = state;
    }
}
