package kxg.album.system.provider.exception;

import kxg.album.system.provider.constant.ReturnCode;

/**
 * 要写注释呀
 */
public class KxgException extends RuntimeException{
    private String code;
    private String msg;
    public KxgException(ReturnCode returnCode){
        super(returnCode.getCode() + " : " + returnCode.getMsg());
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
    }
    public KxgException(String code, String msg){
        super(code + " : " + msg);
        this.code = code;
        this.msg = msg;
    }
}
