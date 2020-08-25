package kxg.album.system.provider;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableApolloConfig
@EnableSwagger2
@EnableScheduling
@MapperScan("kxg.album.system.provider.mapper")
@ImportResource(locations = {"classpath:spring-dubbo.xml"})
@SpringBootApplication
public class DubboProviderBootstrap extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DubboProviderBootstrap.class)
                .run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DubboProviderBootstrap.class);
    }
}