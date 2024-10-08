package org.click.friends.repository;

import org.click.friends.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friends, Long> {

    // 친구 목록 조회
    List<Friends> findByFriendshipIsTrueAndMyCode(String myCode);

    // 친구 요청 목록
    List<Friends> findByFriendshipIsFalseAndTargetCode(String myCode);

    // 이미 친구 요청을 보낸 유저입니다.
    Boolean existsByFriendshipIsFalseAndMyCodeAndTargetCode(String myCode, String targetCode);

    // 이미 친구 사이인 유저입니다.
    Boolean existsByFriendshipIsTrueAndMyCodeAndTargetCode(String myCode, String targetCode);

    // 친구 요청 수락&거절
    Friends findByFriendshipIsFalseAndMyCodeAndTargetCode(String myCode, String targetCode);

    // 친구 삭제
    void deleteByMyCodeAndTargetCode(String myCode, String targetCode);
}
