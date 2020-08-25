package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class SAOpenIdRequest implements Serializable {
    private static final long serialVersionUID = -93283430464640311L;
    private String code;
}
