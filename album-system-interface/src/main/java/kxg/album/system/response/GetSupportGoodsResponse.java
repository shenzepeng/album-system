package kxg.album.system.response;

import kxg.album.system.dto.GoodsDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class GetSupportGoodsResponse implements Serializable {
    private static final long serialVersionUID = -5724198759277712090L;
    private List<GoodsDto> goodsDtos;
}
