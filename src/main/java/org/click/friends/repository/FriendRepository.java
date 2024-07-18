package org.click.friends.repository;

import org.click.friends.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    // 친구 목록 조회
    List<Friend> findByFriendshipIsTrueAndMyCode(String myCode);

    // 친구 요청 목록
    List<Friend> findByFriendshipIsFalseAndTargetCode(String myCode);

    // 이미 친구 요청을 보낸 유저입니다.
    Boolean existsByFriendshipIsFalseAndMyCodeAndTargetCode(String myCode, String targetCode);

    // 이미 친구 사이인 유저입니다.
    Boolean existsByFriendshipIsTrueAndMyCodeAndTargetCode(String myCode, String targetCode);

    // 친구 요청 수락&거절
    Friend findByFriendshipIsFalseAndMyCodeAndTargetCode(String myCode, String targetCode);

    // 친구 삭제
    void deleteByMyCodeAndTargetCode(String myCode, String targetCode);
}
