package org.click.friends.service;

import org.click.friends.global.dto.response.UserListResponse;

import java.util.List;

public interface FriendService {

    // 친구 목록 조회
    List<UserListResponse> getFriends(String myCode);

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
}
