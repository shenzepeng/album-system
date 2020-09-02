package kxg.album.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class FindSecondaryByPhoneNumberRequest implements Serializable {
    private String phoneNumber;
}
