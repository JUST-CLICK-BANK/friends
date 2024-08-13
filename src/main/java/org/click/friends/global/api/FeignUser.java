package org.click.friends.global.api;

import org.click.friends.global.dto.response.UserListResponse;
import org.click.friends.global.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "feignUser", url = "https://just-click.shop/api/v1/auth")
public interface FeignUser {

    @GetMapping(value = "/friends")
    List<UserListResponse> users(
        @RequestParam("codes") String[] codes
    );

    @GetMapping
    UserResponse user(
        @RequestParam("code") String code
    );
}
