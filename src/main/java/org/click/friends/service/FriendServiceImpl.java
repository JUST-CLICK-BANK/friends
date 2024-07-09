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

    @Override
    public List<Friend> getFriends(UUID userId) {
        return friendRepository.findByFriendshipIsTrueAndUserId1(userId);
    }

    @Override
    public void acceptFriendRequest(FriendRequest friendRequest) {
        friendRepository.save(friendRequest.toEntity());
    }

    @Override
    public void confirmFriendRequest() {

    }

    @Override
    public void rejectFriendRequest() {

    }

    @Override
    public void removeFriend() {

    }
}
