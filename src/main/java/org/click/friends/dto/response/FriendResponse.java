package org.click.friends.dto.response;

import org.click.friends.entity.Friend;

import java.util.UUID;

public record FriendResponse(
        Long friend_id,
        Boolean friendship,
        UUID user_id_1,
        UUID user_id_2
) {
    public static FriendResponse from(Friend friend) {
        return new FriendResponse(
                friend.getFriendId(),
                friend.getFriendship(),
                friend.getUserId1(),
                friend.getUserId2()
        );
    }
}
