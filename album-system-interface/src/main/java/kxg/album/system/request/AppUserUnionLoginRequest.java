package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class AppUserUnionLoginRequest implements Serializable {
    private static final long serialVersionUID = 4264425124741235966L;
    private String unionId;
}
