package kxg.album.system.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品名称

     */
    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "content_id")
    private Long contentId;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private Long shopId;

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
     * 获取商品名称

     *
     * @return goods_name - 商品名称

     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称

     *
     * @param goodsName 商品名称

     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
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
     * @return content_id
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * @param contentId
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * 获取店铺id
     *
     * @return shop_id - 店铺id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店铺id
     *
     * @param shopId 店铺id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}