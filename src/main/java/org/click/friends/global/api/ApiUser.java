package org.click.friends.global.api;

import lombok.RequiredArgsConstructor;
import org.click.friends.global.dto.UserListResponse;
import org.click.friends.global.dto.UserResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiUser {
    public final FeignUser feignUser;

    @Async
    public List<UserListResponse> getUsers(String[] codes) {
        return feignUser.users(codes);
    }

    @Async
    public UserResponse getUser(String code) {
        return feignUser.user(code);
    }
}
