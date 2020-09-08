package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class UpdateAppUserInfoRequest implements Serializable {
    private static final long serialVersionUID = 576728453447496384L;


    private Long id;

    /**
     * 普通用户昵称
     */
    private String nickName;

    /**
     * 普通用户性别，1为男性，2为女性


     */
    private Short sex;

    /**
     * 语言
     */
    private String language;

    /**
     * 普通用户个人资料填写的城市
     */
    private String city;

    /**
     * 普通用户个人资料填写的省份
     */
    private String province;

    /**
     * 国家，如中国为CN
     */
    private String country;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    private String headerImgUrl;

    private String phoneNumber;

    private String password;

    /**
     * 特权
     */
    private Long privilege;

    private String openId;

}
