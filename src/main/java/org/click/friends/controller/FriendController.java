package org.click.friends.controller;


import lombok.RequiredArgsConstructor;
import org.click.friends.domain.dto.request.FriendRequest;
import org.click.friends.domain.dto.response.FriendResponse;
import org.click.friends.domain.entity.Friend;
import org.click.friends.service.FriendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
public class FriendController {

    private final FriendService friendService;

    // 친구 목록 조회
    @GetMapping("/{userId}")
    public List<Friend> getFriends(
            @PathVariable("userId")UUID userId
    ) {
        return friendService.getFriends(userId);
    }

    // 친구 요청
    @PostMapping("/request")
    public void acceptFriendRequest(
            @RequestBody FriendRequest friendRequest
    ) {
        friendService.acceptFriendRequest(friendRequest);
    }

    // 친구 요청 수락
    @PutMapping("/request/{friendId}")
    public void confirmFriendRequest(
            @PathVariable("friendId") Long friendId
    ) {
        friendService.confirmFriendRequest(friendId);
    }

    // 친구 요청 삭제
    @DeleteMapping("/request")
    public void rejectFriendRequest() {
        friendService.rejectFriendRequest();
    }

    // 친구 삭제
    @DeleteMapping
    public void removeFriend() {
        friendService.removeFriend();
    }
}
