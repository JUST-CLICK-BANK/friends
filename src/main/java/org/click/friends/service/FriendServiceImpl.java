package org.click.friends.service;

import lombok.RequiredArgsConstructor;
import org.click.friends.domain.dto.response.FriendResponse;
import org.click.friends.domain.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    @Override
    public List<FriendResponse> getAllFriends() {
        return List.of();
    }

    @Override
    public void acceptFriendRequest() {

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
