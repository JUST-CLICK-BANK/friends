package org.click.friends.service;

import lombok.RequiredArgsConstructor;
import org.click.friends.domain.dto.request.FriendRequest;
import org.click.friends.domain.entity.Friend;
import org.click.friends.domain.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    // 친구 목록 조회
    @Override
    public List<Friend> getFriends(UUID userId) {
        return friendRepository.findByFriendshipIsTrueAndUserId1(userId);
    }

    // 친구 요청
    @Override
    public void acceptFriendRequest(FriendRequest friendRequest) {
        friendRepository.save(friendRequest.toEntity());
    }

    // 친구 요청 수락
    @Override
    public void confirmFriendRequest(Long friendId) {
        // TODO 여기서부터
    }

    // 친구 요청 삭제
    @Override
    public void rejectFriendRequest() {

    }

    // 친구 삭제
    @Override
    public void removeFriend() {

    }
}
