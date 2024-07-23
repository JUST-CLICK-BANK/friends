package org.click.friends;

import java.util.UUID;
import org.click.friends.entity.Friends;
import org.click.friends.global.dto.UserResponse;

public class TestInitData {
    protected final Friends friend1;
    protected final Friends friend2;
    protected final UserResponse user;

    public TestInitData() {
        this.friend1 = new Friends(1L, true, "AAAAA", "BBBBB");
        this.friend2 = new Friends(2L, false, "AAAAA", "BBBBB");
        this.user = new UserResponse(
            UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"),
            "BBBBB",
            "img",
            "유저2"
        );
    }
}
