package kxg.album.system.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_agency_relationship")
public class AgencyRelationShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id app userID

     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 父id app User Id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 0 无法使用app 1一级代理  2二级代理
     */
    private Short type;

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
     * 获取用户id app userID

     *
     * @return user_id - 用户id app userID

     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id app userID

     *
     * @param userId 用户id app userID

     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取父id app User Id
     *
     * @return parent_id - 父id app User Id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父id app User Id
     *
     * @param parentId 父id app User Id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取0 一级代理  1二级代理
     *
     * @return type - 0 一级代理  1二级代理
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置0 一级代理  1二级代理
     *
     * @param type 0 一级代理  1二级代理
     */
    public void setType(Short type) {
        this.type = type;
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