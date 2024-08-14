package org.click.friends.service;

import org.click.friends.global.dto.response.UserInfo;
import org.click.friends.global.dto.response.UserListResponse;

import java.util.List;

public interface FriendService {

    // 친구 목록 조회
    UserInfo getFriends(String myCode, String myAccount);

    // 친구 요청
    void acceptFriendRequest(String code, String myCode);

    // 친구 요청 수락
    void confirmFriendRequest(String code, String myCode);

    // 친구 요청 거절
    void rejectFriendRequest(String code, String myCode);

    // 친구 삭제
    void removeFriend(String code, String myCode);

    // 친구 요청 목록
    List<UserListResponse> getFriendRequests(String myCode);

    // 모임통장 - 친구 목록 조회
    List<UserListResponse> inviteAccountFriends(String myCode);
}
