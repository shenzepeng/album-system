package kxg.album.system.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDto implements Serializable {
    private static final long serialVersionUID = -7795342712541322648L;
    private String version;
    private Short type;
    private String url;
}
