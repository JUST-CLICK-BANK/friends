package org.click.friends.dto.request;

import org.click.friends.entity.Friend;

public record ConfirmFriendRequest(
    Long friend_id,
    boolean friendship,
    String target_code
) {

    public Friend toEntity(String myCode) {
        return Friend.builder()
            .friendship(true)
            .myCode(myCode)
            .targetCode(target_code)
            .build();
    }
}
