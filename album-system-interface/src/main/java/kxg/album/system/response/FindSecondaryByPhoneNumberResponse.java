package kxg.album.system.response;

import kxg.album.system.dto.AppUserDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class FindSecondaryByPhoneNumberResponse implements Serializable {
    private static final long serialVersionUID = -6036064288877872083L;
    private List<AppUserDto> userDtoList;
}
