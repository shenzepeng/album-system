package kxg.album.system.provider.dto;

import kxg.album.system.provider.cache.VersionCache;
import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class VersionRoot {
    private String code;

    private String message;

    private VersionData data;
}
