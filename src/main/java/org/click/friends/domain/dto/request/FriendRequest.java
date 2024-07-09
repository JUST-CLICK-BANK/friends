package org.click.friends.domain.dto.request;

import org.click.friends.domain.entity.Friend;

import java.util.UUID;

public record FriendRequest (
        Long friend_id,
        Boolean friendship,
        UUID user_id_1,
        UUID user_id_2
) {
    public Friend toEntity() {
        return Friend.builder()
                .friendship(false)
                .userId1(user_id_1)
                .userId2(user_id_2)
                .build();
    }
}
