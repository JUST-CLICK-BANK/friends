package org.click.friends.global.dto;

import java.util.UUID;

public record UserResponse(
    UUID id,
    String code,
    String img,
    String name
) {

    public static UserResponse from(UUID id, String code, String img, String name) {
        return new UserResponse(
            id,
            code,
            img,
            name
        );
    }
}
