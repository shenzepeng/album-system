package kxg.album.system.provider.dto;

import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class GoodsInfo {
    private List<GoodsDtos> goodsDtos ;

    private int totals;
}
