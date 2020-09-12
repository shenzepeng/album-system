package kxg.album.system.provider.controller;

import kxg.album.system.provider.common.KxgResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("health")
public class HealthController {
    @GetMapping
    public KxgResponse<String> getHealth(){
        return KxgResponse.ok("health");
    }
}
