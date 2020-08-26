package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class AddGoodsOrderRequest  implements Serializable {
    private static final long serialVersionUID = 2722975073000067508L;
    private Long goodsId;
    private Long appUserId;
    private Long addUserId;
}
