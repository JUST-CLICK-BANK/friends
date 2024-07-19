package org.click.friends.dto.request;

import org.click.friends.entity.Friends;

public record ConfirmFriendRequest(
    Long friend_id,
    Boolean friendship,
    String target_code
) {

    public Friends toEntity(String myCode) {
        return Friends.builder()
            .friendship(true)
            .myCode(myCode)
            .targetCode(target_code)
            .build();
    }
}
