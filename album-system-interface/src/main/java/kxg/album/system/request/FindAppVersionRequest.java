package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class FindAppVersionRequest implements Serializable {
    private static final long serialVersionUID = -4710949679839026635L;
    private String code;
    private String system;
}
