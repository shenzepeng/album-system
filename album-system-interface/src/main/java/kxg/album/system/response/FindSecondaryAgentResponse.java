package kxg.album.system.response;

import kxg.album.system.dto.AppUserDto;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class FindSecondaryAgentResponse implements Serializable {
    private static final long serialVersionUID = 7464224305064454091L;
    private List<AppUserDto> userDtoList=new ArrayList<>();
}
