package org.click.friends.service;

import org.click.friends.dto.request.ConfirmFriendRequest;
import org.click.friends.dto.request.FriendRequest;
import org.click.friends.entity.Friend;

import java.util.List;
import java.util.UUID;

public interface FriendService {
    // 친구 목록 조회
    List<Friend> getFriends(UUID userId);

    // 친구 요청
    void acceptFriendRequest(FriendRequest request);

    // 친구 요청 수락
    void confirmFriendRequest(Long friendId, ConfirmFriendRequest request);

    // 친구 요청 거절
    void rejectFriendRequest(Long friendId);

    // 친구 삭제
    void removeFriend(Long friendId);

    // 친구 요청 목록
    List<Friend> getFriendRequests(UUID userId);
}
