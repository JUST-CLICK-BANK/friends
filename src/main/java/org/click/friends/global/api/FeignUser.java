package org.click.friends.global.api;

import org.click.friends.global.dto.UserListResponse;
import org.click.friends.global.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "feignUser", url = "192.168.0.16:8080")
public interface FeignUser {

    @GetMapping(value = "/api/v1/auth/friends")
    List<UserListResponse> users(
            @RequestParam("codes") String[] codes
    );

    @GetMapping(value = "/api/v1/auth")
    UserResponse user(
            @RequestParam("code") String code
    );
}
