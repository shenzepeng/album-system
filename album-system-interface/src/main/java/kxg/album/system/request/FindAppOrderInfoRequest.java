package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class FindAppOrderInfoRequest implements Serializable {
    private static final long serialVersionUID = 1056221823047155727L;
    /**
     * 商户id
     */
    private Long appUserId;
    /**
     * 状态
     */
    private Short status;
    /**
     * 订单号
     */
    private String orderKey;
    private Integer pageNumber=1;
    private Integer pageSize=10;
}
