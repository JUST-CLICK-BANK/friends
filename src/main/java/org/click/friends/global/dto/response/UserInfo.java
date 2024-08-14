package org.click.friends.global.dto.response;

import java.util.List;

public record UserInfo(
    String myAccount,
    List<UserListResponse> friendInfo
) {

    public static UserInfo from(String myAccount, List<UserListResponse> friendInfo) {
        return new UserInfo(
            myAccount,
            friendInfo
        );
    }
}
