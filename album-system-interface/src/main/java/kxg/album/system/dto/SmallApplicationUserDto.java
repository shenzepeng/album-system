package kxg.album.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 小程序用户dto
 */
@Data
public class SmallApplicationUserDto implements Serializable {
    private static final long serialVersionUID = -5434651899994775371L;
    private Long id;

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
}
