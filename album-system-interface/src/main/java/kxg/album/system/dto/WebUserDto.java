package kxg.album.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 要写注释呀
 */
@Data
public class WebUserDto implements Serializable {
    private static final long serialVersionUID = 1660470760240731613L;
    private Long id;


    private String phoneNumber;

    private String password;


    private String nickName;


    private Date createTime;


    private Date updateTime;
}
