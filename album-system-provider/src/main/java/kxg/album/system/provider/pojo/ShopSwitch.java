package kxg.album.system.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_shop_switch")
public class ShopSwitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private String shopId;

    /**
     * 0可以显示  1不可用显示
     */
    private Short status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 店铺名称
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取店铺id
     *
     * @return shop_id - 店铺id
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * 设置店铺id
     *
     * @param shopId 店铺id
     */
    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    /**
     * 获取0可以显示  1不可用显示
     *
     * @return status - 0可以显示  1不可用显示
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置0可以显示  1不可用显示
     *
     * @param status 0可以显示  1不可用显示
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取店铺名称
     *
     * @return shop_name - 店铺名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店铺名称
     *
     * @param shopName 店铺名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }
}