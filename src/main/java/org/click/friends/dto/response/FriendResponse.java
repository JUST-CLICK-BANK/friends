package org.click.friends.dto.response;

import org.click.friends.entity.Friend;

public record FriendResponse(
    Long friend_id,
    boolean friendship,
    String my_code,
    String target_code
) {
}
