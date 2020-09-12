package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class GetSupportGoodsRequest implements Serializable {
    private static final long serialVersionUID = 1393920343236513412L;
    private Integer goodsNums=10;
}
