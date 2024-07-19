package org.click.friends.dto.request;

import org.click.friends.entity.Friends;

public record FriendRequest(
    Long friend_id,
    Boolean friendship,
    String target_code
) {

    public Friends toEntity(String myCode) {
        return Friends.builder()
            .friendship(false)
            .myCode(myCode)
            .targetCode(target_code)
            .build();
    }
}
