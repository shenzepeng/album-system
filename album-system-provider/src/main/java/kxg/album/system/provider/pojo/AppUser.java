package kxg.album.system.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 普通用户昵称
     */
    @Column(name = "nick_name")
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
    @Column(name = "header_img_url")
    private String headerImgUrl;

    /**
     * 联合id
     */
    @Column(name = "union_id")
    private String unionId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    /**
     * 特权
     */
    private Long privilege;

    @Column(name = "open_id")
    private String openId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取普通用户昵称
     *
     * @return nick_name - 普通用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置普通用户昵称
     *
     * @param nickName 普通用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取普通用户性别，1为男性，2为女性


     *
     * @return sex - 普通用户性别，1为男性，2为女性


     */
    public Short getSex() {
        return sex;
    }

    /**
     * 设置普通用户性别，1为男性，2为女性


     *
     * @param sex 普通用户性别，1为男性，2为女性


     */
    public void setSex(Short sex) {
        this.sex = sex;
    }

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    /**
     * 获取普通用户个人资料填写的城市
     *
     * @return city - 普通用户个人资料填写的城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置普通用户个人资料填写的城市
     *
     * @param city 普通用户个人资料填写的城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取普通用户个人资料填写的省份
     *
     * @return province - 普通用户个人资料填写的省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置普通用户个人资料填写的省份
     *
     * @param province 普通用户个人资料填写的省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取国家，如中国为CN
     *
     * @return country - 国家，如中国为CN
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家，如中国为CN
     *
     * @param country 国家，如中国为CN
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 获取用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     *
     * @return header_img_url - 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    public String getHeaderImgUrl() {
        return headerImgUrl;
    }

    /**
     * 设置用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     *
     * @param headerImgUrl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    public void setHeaderImgUrl(String headerImgUrl) {
        this.headerImgUrl = headerImgUrl == null ? null : headerImgUrl.trim();
    }

    /**
     * 获取联合id
     *
     * @return union_id - 联合id
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 设置联合id
     *
     * @param unionId 联合id
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return phone_number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取特权
     *
     * @return privilege - 特权
     */
    public Long getPrivilege() {
        return privilege;
    }

    /**
     * 设置特权
     *
     * @param privilege 特权
     */
    public void setPrivilege(Long privilege) {
        this.privilege = privilege;
    }

    /**
     * @return open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }
}