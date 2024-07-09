package org.click.friends.domain.repository;

import org.click.friends.domain.dto.response.FriendResponse;
import org.click.friends.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    // 친구 목록 조회
    List<Friend> findByFriendshipIsTrueAndUserId1(UUID userId);
    // 친구의 친구 목록에서 나를 삭제
    void deleteByUserId1AndUserId2(UUID userId2, UUID userId1);
}
