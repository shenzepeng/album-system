package kxg.album.system.provider.constant;


import com.google.common.collect.Maps;

import java.util.Map;


public enum ReturnCode {
    SUCCESS("00000", null),
    WEB_USER_PHONE_NUMBER_OR_PASSWORD_IS_NOT_RIGHT("50001","账号或者密码不正确"),
    OPEN_ID_CAN_NOT_BE_NULL("50002","openId不能为null"),
    APP_USER_ID_CAN_NOT_BE_NULL("50003","APP商户id不能为空"),
    PLEASE_CHECK_PHONE_NUMBER_AND_PASSWORD("50004","请检查账号密码"),
    UNION_ID_IS_NOT_RIGHT("50005","union id is not right"),

    QUEST_FAIL("99999","请求失败");

    private String code;
    private String msg;
    private static final Map<String,ReturnCode> CODE_MAP = Maps.newHashMap();
    static{
        ReturnCode[] values = ReturnCode.values();
        for(ReturnCode returnCode : values){
            CODE_MAP.put(returnCode.code,returnCode);
        }
    }
    ReturnCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public String getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public static ReturnCode get(String code){
        return CODE_MAP.get(code);
    }
}
