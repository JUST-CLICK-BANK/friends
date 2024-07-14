package org.click.friends.repository;

import org.click.friends.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    // 친구 목록 조회
    List<Friend> findByFriendshipIsTrueAndMyId(UUID myId);
    // 친구의 친구 목록에서 나를 삭제
    void deleteByMyIdAndTargetId(UUID targetId, UUID myId);
    // 친구 요청 목록
    List<Friend> findByFriendshipIsFalseAndTargetId(UUID myId);
    // 이미 친구 요청을 보낸 유저입니다.
    Boolean existsByFriendshipIsFalseAndMyIdAndTargetId(UUID myId, UUID targetId);
    // 이미 친구 사이인 유저입니다.
    Boolean existsByFriendshipIsTrueAndMyIdAndTargetId(UUID myId, UUID targetId);
}
