package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class FindSecondaryAgentRequest implements Serializable {
    private static final long serialVersionUID = 6341269753119949193L;
    private String unionId;
}
