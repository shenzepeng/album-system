package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class AddSecondaryRequest implements Serializable {
    private static final long serialVersionUID = -2483612607572025134L;
    /**
     * 用户id app userID

     */

    private Long userId;

    /**
     * 父id app User Id
     */

    private Long parentId;
}
