package kxg.album.system.provider.dto;

import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class GoodsDtos {
    private String goodsName;

    private String createTime;

    private String updateTime;

    private String content;

    private List<String> smallPicture ;

    private List<String> bigPicture ;

    private String goodsIndex;
}
