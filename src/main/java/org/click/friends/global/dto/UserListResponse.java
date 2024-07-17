package org.click.friends.global.dto;

import java.util.UUID;

public record UserListResponse(
        UUID id,
        String img,
        String name
) {
    public static UserListResponse from(UUID id, String img, String name) {
        return new UserListResponse(
                id,
                img,
                name
        );
    }
}
