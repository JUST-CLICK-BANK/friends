package org.click.friends.controller;


import lombok.RequiredArgsConstructor;
import org.click.friends.dto.request.ConfirmFriendRequest;
import org.click.friends.dto.request.FriendRequest;
import org.click.friends.entity.Friend;
import org.click.friends.service.FriendService;
import org.springframework.http.ResponseEntity;
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
            @PathVariable("userId")UUID myId
    ) {
        return friendService.getFriends(myId);
    }

    // 친구 요청
    @PostMapping("/request")
    public ResponseEntity<String> acceptFriendRequest(
            @RequestBody FriendRequest request
    ) {
        friendService.acceptFriendRequest(request);
        return ResponseEntity.ok("친구 요청을 보냈습니다.");
    }

    // 친구 요청 수락
    @PutMapping("/request/confirm/{friendId}")
    public ResponseEntity<String> confirmFriendRequest(
            @PathVariable("friendId") Long friendId,
            @RequestBody ConfirmFriendRequest request
    ) {
        friendService.confirmFriendRequest(friendId, request);
        return ResponseEntity.ok("친구 요청을 수락했습니다.");
    }

    // 친구 요청 거절
    @DeleteMapping("/request/reject/{friendId}")
    public ResponseEntity<String> rejectFriendRequest(
            @PathVariable("friendId") Long friendId
    ) {
        friendService.rejectFriendRequest(friendId);
        return ResponseEntity.ok("친구 요청을 거절했습니다.");
    }

    // 친구 삭제
    @DeleteMapping("/{friendId}")
    public ResponseEntity<String> removeFriend(
            @PathVariable("friendId") Long friendId
    ) {
        friendService.removeFriend(friendId);
        return ResponseEntity.ok("삭제되었습니다.");
    }

    // 친구 요청 목록
    @GetMapping("/{userId}/request")
    public List<Friend> getFriendRequests(
            @PathVariable("userId") UUID myId
    ) {
        return friendService.getFriendRequests(myId);
    }
}
