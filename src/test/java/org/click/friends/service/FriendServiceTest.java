package org.click.friends.service;

import jakarta.transaction.Transactional;
import org.click.friends.dto.request.FriendRequest;
import org.click.friends.entity.Friend;
import org.click.friends.global.api.ApiUser;
import org.click.friends.global.dto.UserListResponse;
import org.click.friends.repository.FriendRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class FriendServiceTest {
    @InjectMocks
    private FriendServiceImpl friendService;
    @Mock
    FriendRepository friendRepository;
    @Mock
    ApiUser apiUser;

    @Nested
    @Transactional
    class getFriendList {
        @Test
        void 성공_친구_목록을_조회함() {
            // given
            Friend friend1 = new Friend(1L, true, UUID.fromString("e9b1c7b8-775d-4d7c-b47c-f3c2c43c8658"), UUID.fromString("a0eeb2fc-9994-4e28-b3e3-4c34f88a67f5"));
            Friend friend2 = new Friend(2L, true, UUID.fromString("a0eeb2fc-9994-4e28-b3e3-4c34f88a67f5"), UUID.fromString("e9b1c7b8-775d-4d7c-b47c-f3c2c43c8658"));

            List<Friend> friendList = List.of(friend1, friend2);
            List<UserListResponse> userResponseList = List.of(
                    new UserListResponse(friend1.getMyId(), "AAAAA", "profileImg", "유저1"),
                    new UserListResponse(friend2.getMyId(), "AAAAA", "profileImg", "유저2")
            );
            // Mocking
            Mockito.when(friendRepository.findByFriendshipIsTrueAndMyId(friend1.getMyId())).thenReturn(friendList);
//            Mockito.when(apiUser.getUsers()).thenReturn(userResponseList);

            // when
            List<UserListResponse> friends = friendService.getFriends(friend1.getMyId());
            // then
            Assertions.assertEquals(2, friends.size());
            Assertions.assertEquals(UUID.fromString("e9b1c7b8-775d-4d7c-b47c-f3c2c43c8658"), friends.get(0).id());
            Assertions.assertEquals(UUID.fromString("a0eeb2fc-9994-4e28-b3e3-4c34f88a67f5"), friends.get(1).id());
        }
    }

    @Nested
    @Transactional
    class sendFriendRequest {
        @Test
        void 성공_친구_요청을_보냄() {
            // given
            UUID myId = UUID.fromString("e9b1c7b8-775d-4d7c-b47c-f3c2c43c8658");
            UUID targetId = UUID.fromString("a0eeb2fc-9994-4e28-b3e3-4c34f88a67f5");

            Friend friend = new Friend(1L, false, myId, targetId);
            FriendRequest friendRequest = new FriendRequest(friend.getFriendId(), friend.getFriendship(), friend.getTargetId());
            // TODO Mocking? 아무튼 여기서부터
            String code = "AAAAA";

            // when
            friendService.acceptFriendRequest(code, myId);

            // then
            Mockito.verify(friendRepository).save(Mockito.any(Friend.class));
        }

        @Test
        void 실패_이미_요청한_유저() {

        }

        @Test
        void 실패_이미_친구인_유저() {

        }
    }

    @Nested
    @Transactional
    class acceptFriendRequest {
        void 성공_받은_요청을_수락함() {

        }
    }

    @Nested
    @Transactional
    class rejectFriendRequest {
        void 성공_받은_요청을_거절함() {

        }
    }

    @Nested
    @Transactional
    class getReceivedFriendRequests {
        void 성공_받은_요청_목록을_조회함() {

        }
    }

    @Nested
    @Transactional
    class deleteFriend {
        void 성공_친구를_삭제함() {

        }

        void 실패_이미_친구가_아닌_유저() {

        }
    }
}