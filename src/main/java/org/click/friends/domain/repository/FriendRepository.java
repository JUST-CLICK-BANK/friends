package org.click.friends.domain.repository;

import org.click.friends.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByFriendshipIsTrueAndUserId1(UUID userId);
}
