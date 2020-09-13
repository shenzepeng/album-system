package kxg.album.system.provider.task;

import com.ctrip.framework.apollo.ConfigService;
import kxg.album.system.provider.cache.VersionCache;
import kxg.album.system.provider.dto.VersionData;
import kxg.album.system.provider.dto.VersionRoot;


import kxg.album.system.provider.utils.HttpClientUtil;
import kxg.album.system.provider.utils.JsonUtils;
import kxg.album.system.request.FindAppVersionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spring.web.json.Json;

/**
 * 要写注释呀
 */
@Component
@Slf4j
public class VersionTask {

    @Scheduled(fixedRate = 5)
    public void getNewVersion(){
        String versionUrl = ConfigService.getAppConfig().getProperty("get.app.version.url", "");
        log.info("get.app.version.url {}",versionUrl);
        if (StringUtils.isEmpty(versionUrl)){
            return;
        }
        String code=ConfigService.getAppConfig().getProperty("get.app.version.code","");
        String system=ConfigService.getAppConfig().getProperty("get.app.os.system","");
        FindAppVersionRequest request=new FindAppVersionRequest();
        request.setCode(code);
        request.setSystem(system);
        String doPost = HttpClientUtil.doPostJson(versionUrl, JsonUtils.objectToJson(request));
        VersionRoot versionRoot = JsonUtils.jsonToPojo(doPost, VersionRoot.class);
        log.info("VersionRoot {},FindAppVersionRequest request {}",versionRoot,request);
        VersionCache.AndroidVersionType=versionRoot.getData().getVersionDto().getType();
        VersionCache.AndroidVersion=versionRoot.getData().getVersionDto().getVersion();
        VersionCache.AndroidUrl=versionRoot.getData().getVersionDto().getUrl();
        log.info("version cache {}",versionRoot);
    }
}
