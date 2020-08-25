package kxg.album.system.dto;

import lombok.Data;
import java.io.Serializable;
@Data
public class WXSessionModelDto implements Serializable {
	private static final long serialVersionUID = 5891539291566818875L;
	private String session_key;
	private String openid;
}
