package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 小程序
 */
@Data
public class AddSAUserRequest implements Serializable {
    private static final long serialVersionUID = -4018251257856691275L;
    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 小程序唯一标识
     */
    private String openId;

    /**
     * 城市
     */
    private String city;

    /**
     * 性别
     */
    private Short gender;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 省份
     */
    private String province;

    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 商家用户id
     */
    private Long appUserId;

}
