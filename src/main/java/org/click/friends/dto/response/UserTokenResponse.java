package org.click.friends.dto.response;

public record UserTokenResponse(
    String myCode,
    String myAccount
) {
    public static UserTokenResponse from(
        String myCode,
        String myAccount
    ) {
        return new UserTokenResponse(
            myCode,
            myAccount
        );
    }
}
