package org.click.friends.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FriendErrorCode {
    NOT_FOUND_USER("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ALREADY_REQUEST("이미 친구 요청을 보낸 유저입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_FRIEND("이미 친구 사이인 유저입니다.", HttpStatus.BAD_REQUEST),
    THIS_IS_ME("자기 자신입니다.", HttpStatus.BAD_REQUEST);

    private final String errorMessage;
    private final HttpStatus httpStatus;
}
