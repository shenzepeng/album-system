package kxg.album.system.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods_order")
public class GoodsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 订单key

     */
    @Column(name = "order_key")
    private String orderKey;

    /**
     * 小程序用户表id
     */
    @Column(name = "add_user_id")
    private Long addUserId;

    /**
     * app用户id
     */
    @Column(name = "app_user_id")
    private Long appUserId;

    /**
     * 0 未支付  1已支付  2退款
     */
    private Short status;

    /**
     * 管理员用户id
     */
    @Column(name = "web_user_id")
    private Long webUserId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取订单key

     *
     * @return order_key - 订单key

     */
    public String getOrderKey() {
        return orderKey;
    }

    /**
     * 设置订单key

     *
     * @param orderKey 订单key

     */
    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey == null ? null : orderKey.trim();
    }

    /**
     * 获取小程序用户表id
     *
     * @return add_user_id - 小程序用户表id
     */
    public Long getAddUserId() {
        return addUserId;
    }

    /**
     * 设置小程序用户表id
     *
     * @param addUserId 小程序用户表id
     */
    public void setAddUserId(Long addUserId) {
        this.addUserId = addUserId;
    }

    /**
     * 获取app用户id
     *
     * @return app_user_id - app用户id
     */
    public Long getAppUserId() {
        return appUserId;
    }

    /**
     * 设置app用户id
     *
     * @param appUserId app用户id
     */
    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    /**
     * 获取0 未支付  1已支付  2退款
     *
     * @return status - 0 未支付  1已支付  2退款
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置0 未支付  1已支付  2退款
     *
     * @param status 0 未支付  1已支付  2退款
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取管理员用户id
     *
     * @return web_user_id - 管理员用户id
     */
    public Long getWebUserId() {
        return webUserId;
    }

    /**
     * 设置管理员用户id
     *
     * @param webUserId 管理员用户id
     */
    public void setWebUserId(Long webUserId) {
        this.webUserId = webUserId;
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
}