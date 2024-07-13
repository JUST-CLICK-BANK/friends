package org.click.friends.dto.request;

import org.click.friends.entity.Friend;

import java.util.UUID;

public record ConfirmFriendRequest(
        Long friend_id,
        Boolean friendship,
        UUID my_id,
        UUID target_id
) {
    public Friend toEntity() {
        return Friend.builder()
                .friendship(true)
                .myId(my_id)
                .targetId(target_id)
                .build();
    }
}
