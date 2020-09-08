package kxg.album.system.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 要写注释呀
 */
@Data
public class AppUserLoginResponse implements Serializable {

    private static final long serialVersionUID = 2981162340463913273L;
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
    /**
     * 联合id
     */
    private String unionId;

    private String phoneNumber;

    private String openId;

    private String token;

    private Short type;
}
