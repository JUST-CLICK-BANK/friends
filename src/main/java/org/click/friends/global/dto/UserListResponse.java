package org.click.friends.global.dto;

import java.util.UUID;

public record UserListResponse(
    UUID id,
    String code,
    String img,
    String name,
    String account
) {

    public static UserListResponse from(UUID id, String code, String img, String name, String account) {
        return new UserListResponse(
            id,
            code,
            img,
            name,
            account
        );
    }
}
