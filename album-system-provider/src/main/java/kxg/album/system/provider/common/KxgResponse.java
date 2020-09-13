package kxg.album.system.provider.common;
import kxg.album.system.provider.cache.VersionCache;
import kxg.album.system.provider.constant.ReturnCode;
import kxg.album.system.provider.dto.VersionDto;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
@Component
@Scope("prototype")
public class KxgResponse<T> {
    private String code;
    private String message;
    private T data;
    private VersionDto version;
    public KxgResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public KxgResponse(String code, String message, T data, VersionDto versionDto) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.version = versionDto;
    }

    public static <K extends Serializable> KxgResponse error(K data){
        return create(ReturnCode.QUEST_FAIL, data);
    }
    public static <K extends Serializable> KxgResponse ok(K data){
        return create(ReturnCode.SUCCESS, data);
    }
    public static KxgResponse ok(){
        return create(ReturnCode.SUCCESS, null);
    }
    public static <K extends Serializable> KxgResponse create(ReturnCode returnCode, K data){
        return new KxgResponse(returnCode.getCode(), returnCode.getMsg(), data,new VersionDto(VersionCache.AndroidVersion,VersionCache.AndroidVersionType,VersionCache.AndroidUrl));
    }
    public static KxgResponse create(ReturnCode returnCode){
        return new KxgResponse(returnCode.getCode(), returnCode.getMsg(),null,new VersionDto(VersionCache.AndroidVersion,VersionCache.AndroidVersionType,VersionCache.AndroidUrl));
    }
}
