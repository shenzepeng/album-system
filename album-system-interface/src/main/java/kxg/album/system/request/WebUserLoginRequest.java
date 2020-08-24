package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class WebUserLoginRequest implements Serializable {
    private static final long serialVersionUID = 5096764341964593811L;
    private String phoneNumber;
    private String password;
}
