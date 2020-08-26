package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class FindAllGoodsRequest implements Serializable {
    private static final long serialVersionUID = 4263977922075524405L;
    private String goodsName;
    private Integer pageNumber=1;
    private Integer pageSize=20;
}
