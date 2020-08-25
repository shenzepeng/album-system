package kxg.album.system.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 要写注释呀
 */
@Data
public class AddUserResponse implements Serializable {
    private static final long serialVersionUID = 73309115043897489L;
    private Long id;


    private String phoneNumber;

    private String password;


    private String nickName;

}
