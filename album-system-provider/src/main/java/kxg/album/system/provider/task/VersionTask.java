package kxg.album.system.provider.task;

import com.ctrip.framework.apollo.ConfigService;
import kxg.album.system.provider.cache.VersionCache;
import kxg.album.system.provider.dto.VersionData;
import kxg.album.system.provider.dto.VersionRoot;
import kxg.album.system.provider.utils.HttpClientUtil;
import kxg.album.system.provider.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 要写注释呀
 */
@Component
@Slf4j
public class VersionTask {
    @Scheduled(fixedRate = 5000)
    public void getNewVersion(){
        String versionUrl = ConfigService.getAppConfig().getProperty("get.app.version.url", "");
        if (StringUtils.isEmpty(versionUrl)){
            return;
        }
        String doPost = HttpClientUtil.doPost(versionUrl);
        VersionRoot versionRoot = JsonUtils.jsonToPojo(doPost, VersionRoot.class);
        VersionCache.type=versionRoot.getData().getVersionDto().getType();
        VersionCache.version=versionRoot.getData().getVersionDto().getVersion();
        VersionCache.url=versionRoot.getData().getVersionDto().getUrl();
        log.info("version cache {}",versionRoot);
    }
}
