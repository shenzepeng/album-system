package kxg.album.system.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class UpdateAppUserInfoResponse implements Serializable {
    private static final long serialVersionUID = 6331536469900493370L;
    /**
     * 0 success
     * 1 fail
     */
    private Integer result;
}
