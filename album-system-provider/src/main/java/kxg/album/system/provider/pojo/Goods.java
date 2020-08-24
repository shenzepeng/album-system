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

    /**
     * 开关id
     */
    @Column(name = "shop_ship_id")
    private Long shopShipId;

    @Column(name = "content_id")
    private Long contentId;

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
     * 获取开关id
     *
     * @return shop_ship_id - 开关id
     */
    public Long getShopShipId() {
        return shopShipId;
    }

    /**
     * 设置开关id
     *
     * @param shopShipId 开关id
     */
    public void setShopShipId(Long shopShipId) {
        this.shopShipId = shopShipId;
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
}