package kxg.album.system.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 要写注释呀
 */
@Data
public class WebUserLoginResponse implements Serializable {
    private static final long serialVersionUID = 7912983205987653942L;
    private Long id;


    private String phoneNumber;

    private String password;


    private String nickName;


    private Date createTime;


    private Date updateTime;
}
