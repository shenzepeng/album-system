package kxg.album.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class GoodsDto implements Serializable {
    private static final long serialVersionUID = 2726801459175718404L;
    private String goodsName;
    private Date createTime;
    private Date updateTime;
    private String content;
    private PictureDto smallPic;
    private PictureDto bigPicture;
}
