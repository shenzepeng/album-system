package kxg.album.system.response;

import kxg.album.system.dto.GoodsDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class FindAllGoodsResponse implements Serializable {
    private static final long serialVersionUID = -9046361589480583402L;
    private List<GoodsDto> goodsDtos;
    private long total;
}
