package org.click.friends.dto.request;

import org.click.friends.entity.Friend;

public record FriendRequest(
    Long friend_id,
    Boolean friendship,
    String target_code
) {

    public Friend toEntity(String myCode) {
        return Friend.builder()
            .friendship(false)
            .myCode(myCode)
            .targetCode(target_code)
            .build();
    }
}
