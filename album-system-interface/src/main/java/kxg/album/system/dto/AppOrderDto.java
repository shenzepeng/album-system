package kxg.album.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商户订单
 */
@Data
public class AppOrderDto  implements Serializable {
    private static final long serialVersionUID = -7749998795544636442L;
    /**
     * 订单id
     */
    private String goodsName;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品内容
     */
    private String content;
    /**
     * 小图
     */
    private List<String> smallPicture;
    /**
     * 大图
     */
    private List<String> bigPicture;
    /**
     * 订单key
     */
    private String orderKey;
    /**
     * 订单状态
     */
    private Short status;
    /**
     * 小程序用户信息
     */
    private SmallApplicationUserDto smallApplicationUserDto;

}
