package kxg.album.system.response;


import kxg.album.system.dto.AppOrderDto;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class FindAppOrderInfoResponse implements Serializable {
    private static final long serialVersionUID = 3344351565377897291L;
    private List<AppOrderDto> dtos;
    private long total;
}
