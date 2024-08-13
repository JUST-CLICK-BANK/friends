package org.click.friends.global.dto.response;

import java.util.UUID;

public record UserResponse(
    UUID id,
    String code,
    String img,
    String name,
    String account
) {

    public static UserResponse from(UUID id, String code, String img, String name, String account) {
        return new UserResponse(
            id,
            code,
            img,
            name,
            account
        );
    }
}
