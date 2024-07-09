package org.click.friends.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.click.friends.domain.dto.request.ConfirmFriendRequest;
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
    public void acceptFriendRequest(FriendRequest request) {
        friendRepository.save(request.toEntity());
    }

    // 친구 요청 수락
    @Override
    @Transactional
    public void confirmFriendRequest(Long friendId, ConfirmFriendRequest request) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(IllegalArgumentException::new);
        friend.setFriendship(true);
        friendRepository.save(friend); // 기존 데이터 업데이트
        friendRepository.save(request.toEntity()); // user_id 순서 바꿔서 새로 저장
    }

    // 친구 요청 삭제
    @Override
    public void rejectFriendRequest(Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(IllegalArgumentException::new);
        friendRepository.delete(friend);
    }

    // 친구 삭제
    @Override
    public void removeFriend() {

    }
}
