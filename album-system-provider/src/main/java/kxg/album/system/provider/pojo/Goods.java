package kxg.album.system.provider.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
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

    private String content;

    /**
     * 类型id
     */
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "goods_index")
    private String goodsIndex;

    @Column(name = "small_pic")
    private String smallPic;

    @Column(name = "big_pic")
    private String bigPic;

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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取类型id
     *
     * @return type_id - 类型id
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * 设置类型id
     *
     * @param typeId 类型id
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * @return goods_index
     */
    public String getGoodsIndex() {
        return goodsIndex;
    }

    /**
     * @param goodsIndex
     */
    public void setGoodsIndex(String goodsIndex) {
        this.goodsIndex = goodsIndex == null ? null : goodsIndex.trim();
    }

    /**
     * @return small_pic
     */
    public String getSmallPic() {
        return smallPic;
    }

    /**
     * @param smallPic
     */
    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic == null ? null : smallPic.trim();
    }

    /**
     * @return big_pic
     */
    public String getBigPic() {
        return bigPic;
    }

    /**
     * @param bigPic
     */
    public void setBigPic(String bigPic) {
        this.bigPic = bigPic == null ? null : bigPic.trim();
    }
}