package kxg.album.system.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class SAOpenIdResponse implements Serializable {
    private static final long serialVersionUID = 6151665000208503364L;
    private String openId;
}
