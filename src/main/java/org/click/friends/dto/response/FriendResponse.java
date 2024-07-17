package org.click.friends.dto.response;

import org.click.friends.entity.Friend;

public record FriendResponse(
        Long friend_id,
        Boolean friendship,
        String my_code,
        String target_code
) {
    public static FriendResponse from(Friend friend) {
        return new FriendResponse(
                friend.getFriendId(),
                friend.getFriendship(),
                friend.getMyCode(),
                friend.getTargetCode()
        );
    }
}
