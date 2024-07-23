package org.click.friends.dto.response;

public record FriendResponse(
    Long friend_id,
    boolean friendship,
    String my_code,
    String target_code
) {
}
