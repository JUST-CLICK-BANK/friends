package org.click.friends.controller;


import lombok.RequiredArgsConstructor;
import org.click.friends.dto.request.ConfirmFriendRequest;
import org.click.friends.dto.request.FriendRequest;
import org.click.friends.entity.Friend;
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
            @RequestBody FriendRequest request
    ) {
        friendService.acceptFriendRequest(request);
    }

    // 친구 요청 수락
    @PutMapping("/request/{friendId}")
    public void confirmFriendRequest(
            @PathVariable("friendId") Long friendId,
            @RequestBody ConfirmFriendRequest request
    ) {
        friendService.confirmFriendRequest(friendId, request);
    }

    // 친구 요청 삭제
    @DeleteMapping("/request/{friendId}")
    public void rejectFriendRequest(
            @PathVariable("friendId") Long friendId
    ) {
        friendService.rejectFriendRequest(friendId);
    }

    // 친구 삭제
    @DeleteMapping("/{friendId}")
    public void removeFriend(
            @PathVariable("friendId") Long friendId
    ) {
        friendService.removeFriend(friendId);
    }

    // 친구 요청 목록
    @GetMapping("/{userId}/request")
    public List<Friend> getFriendRequests(
            @PathVariable("userId") UUID userId
    ) {
        return friendService.getFriendRequests(userId);
    }
}
