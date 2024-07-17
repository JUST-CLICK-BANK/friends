package org.click.friends.controller;


import lombok.RequiredArgsConstructor;
import org.click.friends.global.JwtUtils;
import org.click.friends.global.dto.UserListResponse;
import org.click.friends.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
public class FriendController {

    private final FriendService friendService;
    private final JwtUtils jwtUtils;

    // 친구 목록 조회
    @GetMapping
    public List<UserListResponse> getFriends(
            @RequestHeader("Authorization") String token
    ) {
        if(token != null && token.startsWith("Bearer ")) {
            String bearerToken = token.substring(7);
            String myCode = jwtUtils.parseToken(bearerToken).getMyCode();
            return friendService.getFriends(myCode);
        } else {
            throw new IllegalArgumentException("로그인 후 다시 이용해 주세요.");
        }
    }

    // 친구 요청
    @PostMapping("/request/{code}")
    public ResponseEntity<String> acceptFriendRequest(
            @RequestHeader("Authorization") String token,
            @PathVariable("code") String code
    ) {
        if(token != null && token.startsWith("Bearer ")) {
            String bearerToken = token.substring(7);
            String myCode = jwtUtils.parseToken(bearerToken).getMyCode();
            friendService.acceptFriendRequest(code, myCode);
            return ResponseEntity.ok("친구 요청을 보냈습니다.");
        } else {
            throw new IllegalArgumentException("로그인 후 다시 이용해 주세요.");
        }
    }

    // 친구 요청 수락
    @PutMapping("/request/confirm/{code}")
    public ResponseEntity<String> confirmFriendRequest(
            @RequestHeader("Authorization") String token,
            @PathVariable("code") String code
    ) {
        if(token != null && token.startsWith("Bearer ")) {
            String bearerToken = token.substring(7);
            String myCode = jwtUtils.parseToken(bearerToken).getMyCode();
            friendService.confirmFriendRequest(code, myCode);
            return ResponseEntity.ok("친구 요청을 수락했습니다.");
        } else {
            throw new IllegalArgumentException("로그인 후 다시 이용해 주세요.");
        }
    }

    // 친구 요청 거절
    @DeleteMapping("/request/reject/{code}")
    public ResponseEntity<String> rejectFriendRequest(
            @RequestHeader("Authorization") String token,
            @PathVariable("code") String code
    ) {
        if(token != null && token.startsWith("Bearer ")) {
            String bearerToken = token.substring(7);
            String myCode = jwtUtils.parseToken(bearerToken).getMyCode();
            friendService.rejectFriendRequest(code, myCode);
            return ResponseEntity.ok("친구 요청을 거절했습니다.");
        } else {
            throw new IllegalArgumentException("로그인 후 다시 이용해 주세요.");
        }
    }

    // 친구 삭제
    @DeleteMapping("/{code}")
    public ResponseEntity<String> removeFriend(
            @RequestHeader("Authorization") String token,
            @PathVariable("code") String code
    ) {
        if(token != null && token.startsWith("Bearer ")) {
            String bearerToken = token.substring(7);
            String myCode = jwtUtils.parseToken(bearerToken).getMyCode();
            friendService.removeFriend(code, myCode);
            return ResponseEntity.ok("삭제되었습니다.");
        } else {
            throw new IllegalArgumentException("로그인 후 다시 이용해 주세요.");
        }
    }

    // 친구 요청 목록
    @GetMapping("/request")
    public List<UserListResponse> getFriendRequests(
            @RequestHeader("Authorization") String token
    ) {
        if(token != null && token.startsWith("Bearer ")) {
            String bearerToken = token.substring(7);
            String myCode = jwtUtils.parseToken(bearerToken).getMyCode();
            return friendService.getFriendRequests(myCode);
        } else {
            throw new IllegalArgumentException("로그인 후 다시 이용해 주세요.");
        }
    }
}
