package org.click.friends.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendException extends RuntimeException {
    private FriendErrorCode errorCode;
    private String errorMessage;

    public FriendException(FriendErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getErrorMessage();
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
