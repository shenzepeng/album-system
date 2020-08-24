package kxg.album.system.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_shop_relation")
public class UserShopRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户的userId 小程序的主键
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * app的userId 商户的主键
     */
    @Column(name = "app_user_id")
    private Long appUserId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取客户的userId 小程序的主键
     *
     * @return user_id - 客户的userId 小程序的主键
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置客户的userId 小程序的主键
     *
     * @param userId 客户的userId 小程序的主键
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取app的userId 商户的主键
     *
     * @return app_user_id - app的userId 商户的主键
     */
    public Long getAppUserId() {
        return appUserId;
    }

    /**
     * 设置app的userId 商户的主键
     *
     * @param appUserId app的userId 商户的主键
     */
    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
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
}