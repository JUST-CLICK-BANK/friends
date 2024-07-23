package org.click.friends.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendErrorResponse {
    private FriendErrorCode errorCode;
    private String errorMessage;
}
