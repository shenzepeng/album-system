package kxg.album.system.provider.common;

import kxg.album.system.provider.constant.ReturnCode;
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
public class KxgResponse<T> {
    private String code;
    private String message;
    private T data;

    public static KxgResponse error(){
        return create(ReturnCode.QUEST_FAIL, null);
    }
    public static <K extends Serializable> KxgResponse ok(K data){
        return create(ReturnCode.SUCCESS, data);
    }
    public static KxgResponse ok(){
        return create(ReturnCode.SUCCESS, null);
    }
    public static <K extends Serializable> KxgResponse create(ReturnCode returnCode, K data){
        return new KxgResponse(returnCode.getCode(), returnCode.getMsg(), data);
    }
    public static KxgResponse create(ReturnCode returnCode){
        return new KxgResponse(returnCode.getCode(), returnCode.getMsg(), null);
    }
}
