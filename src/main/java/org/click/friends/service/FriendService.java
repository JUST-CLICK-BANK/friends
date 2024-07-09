package org.click.friends.service;

import org.click.friends.domain.dto.response.FriendResponse;

import java.util.List;
import java.util.UUID;

public interface FriendService {
    // 친구 조회
    List<FriendResponse> getAllFriends();

    // 친구 요청
    void acceptFriendRequest();

    // 친구 요청 수락
    void confirmFriendRequest();

    // 친구 요청 거절
    void rejectFriendRequest();

    // 친구 삭제
    void removeFriend();
}
