package org.click.friends.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.click.friends.dto.request.ConfirmFriendRequest;
import org.click.friends.dto.request.FriendRequest;
import org.click.friends.entity.Friend;
import org.click.friends.global.api.ApiUser;
import org.click.friends.global.dto.UserListResponse;
import org.click.friends.global.dto.UserResponse;
import org.click.friends.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    private final ApiUser apiUser;

    // 친구 목록 조회
    @Override
    public List<UserListResponse> getFriends(String myCode) {
        List<Friend> friendList = friendRepository.findByFriendshipIsTrueAndMyCode(myCode);

        String[] collect = friendList.stream()
            .map(Friend::getTargetCode)
            .toArray(String[]::new);

        return apiUser.getUsers(collect);
    }

    // 친구 요청
    @Override
    public void acceptFriendRequest(String code, String myCode) {
        UserResponse user = apiUser.getUser(code);

        if (user == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }
        FriendRequest request = new FriendRequest(null, false, code);
        // 이미 친구 요청을 보낸 유저입니다.
        if (friendRepository.existsByFriendshipIsFalseAndMyCodeAndTargetCode(myCode, code)
            || friendRepository.existsByFriendshipIsFalseAndMyCodeAndTargetCode(code, myCode)) {
            throw new IllegalArgumentException("이미 친구 요청을 보낸 유저입니다.");
        }
        // 이미 친구 사이인 유저입니다.
        if (friendRepository.existsByFriendshipIsTrueAndMyCodeAndTargetCode(myCode, code)) {
            throw new IllegalArgumentException("이미 친구 사이인 유저입니다.");
        }
        // 자기 자신입니다.
        if (code.equals(myCode)) {
            throw new IllegalArgumentException("자기 자신입니다.");
        }

        friendRepository.save(request.toEntity(myCode));
    }

    // 친구 요청 수락
    @Override
    @Transactional
    public void confirmFriendRequest(String code, String myCode) {
        UserResponse user = apiUser.getUser(code);
        if (user == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        ConfirmFriendRequest request = new ConfirmFriendRequest(null, true, code);
        Friend friend = friendRepository.findByFriendshipIsFalseAndMyCodeAndTargetCode(code,
            myCode);
        friend.setFriendship(true);

        friendRepository.save(friend); // 기존 데이터 업데이트
        friendRepository.save(request.toEntity(myCode)); // user_id 순서 바꿔서 새로 저장
    }

    // 친구 요청 삭제
    @Override
    public void rejectFriendRequest(String code, String myCode) {
        UserResponse user = apiUser.getUser(code);
        if (user == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        Friend friend = friendRepository.findByFriendshipIsFalseAndMyCodeAndTargetCode(code,
            myCode);

        friendRepository.delete(friend);
    }

    // 친구 삭제
    @Override
    @Transactional
    public void removeFriend(String code, String myCode) {
        UserResponse user = apiUser.getUser(code);
        if (user == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        friendRepository.deleteByMyCodeAndTargetCode(myCode, code);
        friendRepository.deleteByMyCodeAndTargetCode(code, myCode);
    }

    // 친구 요청 목록
    @Override
    public List<UserListResponse> getFriendRequests(String myCode) {
        List<Friend> requestFriendList = friendRepository.findByFriendshipIsFalseAndTargetCode(
            myCode);

        String[] collect = requestFriendList.stream()
            .map(Friend::getMyCode)
            .toArray(String[]::new);

        return apiUser.getUsers(collect);
    }
}
