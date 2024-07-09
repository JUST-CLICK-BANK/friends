package org.click.friends.domain.dto.response;

import org.click.friends.domain.entity.Friend;

import java.util.UUID;

public record FriendResponse(
        Long friend_id,
        Boolean friendship,
        UUID user_id_1,
        UUID user_id_2
) {
    public static FriendResponse from(Friend friend) {
        return new FriendResponse(
                friend.getFriend_id(),
                friend.getFriendship(),
                friend.getUser_id_1(),
                friend.getUser_id_2()
        );
    }
}
