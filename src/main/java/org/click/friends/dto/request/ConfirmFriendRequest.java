package org.click.friends.dto.request;

import org.click.friends.entity.Friend;

import java.util.UUID;

public record ConfirmFriendRequest(
        Long friend_id,
        Boolean friendship,
        UUID user_id_1,
        UUID user_id_2
) {
    public Friend toEntity() {
        return Friend.builder()
                .friendship(true)
                .userId1(user_id_2)
                .userId2(user_id_1)
                .build();
    }
}
