package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class UpdateAryRequest implements Serializable {
    private static final long serialVersionUID = 4059144408094085488L;
    private Long id;
    private Short status;
}
