package org.click.friends.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.click.friends.dto.request.ConfirmFriendRequest;
import org.click.friends.dto.request.FriendRequest;
import org.click.friends.entity.Friend;
import org.click.friends.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    // 친구 목록 조회
    @Override
    public List<Friend> getFriends(UUID myId) {
        return friendRepository.findByFriendshipIsTrueAndMyId(myId);
    }

    // 친구 요청
    @Override
    public void acceptFriendRequest(FriendRequest request) {
        // 이미 친구 요청을 보낸 유저입니다.
        if(friendRepository.existsByFriendshipIsFalseAndMyIdAndTargetId(request.my_id(), request.target_id()))
            throw new IllegalArgumentException("이미 친구 요청을 보낸 유저입니다.");
        // 이미 친구 사이인 유저입니다.
        if(friendRepository.existsByFriendshipIsTrueAndMyIdAndTargetId(request.my_id(), request.target_id()))
            throw new IllegalArgumentException("이미 친구 사이인 유저입니다.");
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
    @Transactional
    public void removeFriend(Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(IllegalArgumentException::new);

        friendRepository.deleteByMyIdAndTargetId(friend.getTargetId(), friend.getMyId());
        friendRepository.delete(friend);
    }

    // 친구 요청 목록
    @Override
    public List<Friend> getFriendRequests(UUID myId) {
        return friendRepository.findByFriendshipIsFalseAndTargetId(myId);
    }
}
