package org.click.friends.dto.response;

import org.click.friends.entity.Friend;

import java.util.UUID;

public record FriendResponse(
        Long friend_id,
        Boolean friendship,
        UUID my_id,
        UUID target_id
) {
    public static FriendResponse from(Friend friend) {
        return new FriendResponse(
                friend.getFriendId(),
                friend.getFriendship(),
                friend.getMyId(),
                friend.getTargetId()
        );
    }
}
