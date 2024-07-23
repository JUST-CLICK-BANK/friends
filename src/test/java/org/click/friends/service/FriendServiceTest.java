package org.click.friends.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import jakarta.transaction.Transactional;
import org.click.friends.TestInitData;
import org.click.friends.dto.request.ConfirmFriendRequest;
import org.click.friends.global.api.ApiUser;
import org.click.friends.repository.FriendRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class FriendServiceTest extends TestInitData {
    @InjectMocks
    private FriendServiceImpl friendService;
    @Mock
    FriendRepository friendRepository;
    @Mock
    ApiUser apiUser;

    String myCode = "AAAAA";
    String targetCode = "BBBBB";

    @Nested
    class getFriendList {
        @Test
        void 성공_친구_목록_조회_친구가_있음() {
            // given
            BDDMockito.given(friendRepository.findByFriendshipIsTrueAndMyCode(myCode))
                .willReturn(List.of(friend1));

            // when
            friendService.getFriends(myCode);

            // then
            Mockito.verify(friendRepository, Mockito.times(1))
                .findByFriendshipIsTrueAndMyCode(myCode);
        }

        @Test
        void 성공_친구_목록_조회_친구가_없음() {
            // given
            BDDMockito.given(friendRepository.findByFriendshipIsTrueAndMyCode(myCode))
                .willReturn(List.of());

            // when
            friendService.getFriends(myCode);

            // then
            Mockito.verify(friendRepository, Mockito.times(1))
                .findByFriendshipIsTrueAndMyCode(myCode);
        }
    }

    @Nested
    @Transactional
    class sendFriendRequest {
        @Test
        void 성공_친구_요청을_보냄() {
            // given
            BDDMockito.given(friendRepository.existsByFriendshipIsFalseAndMyCodeAndTargetCode(myCode, targetCode))
                .willReturn(false);
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(user);

            // when
            friendService.acceptFriendRequest(targetCode, myCode);

            // then
            Mockito.verify(friendRepository, Mockito.times(1))
                .save(any());
        }

        @Test
        void 실패_없는_유저() {
            // given
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(null);

            // when
            assertThrows(IllegalArgumentException.class, () -> friendService.acceptFriendRequest(targetCode, myCode));

            // then
            Mockito.verify(friendRepository, Mockito.never())
                .save(Mockito.any());
        }

        @Test
        void 실패_이미_요청한_유저() {
            // given
            BDDMockito.given(friendRepository.existsByFriendshipIsFalseAndMyCodeAndTargetCode(myCode, targetCode))
                .willReturn(true);
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(user);

            // when
            assertThrows(IllegalArgumentException.class, () -> {
                friendService.acceptFriendRequest(targetCode, myCode);
            });

            // then
            Mockito.verify(friendRepository, Mockito.never())
                .save(Mockito.any());
        }

        @Test
        void 실패_이미_친구인_유저() {
            // given
            BDDMockito.given(friendRepository.existsByFriendshipIsTrueAndMyCodeAndTargetCode(myCode, targetCode))
                .willReturn(true);
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(user);

            // when
            assertThrows(IllegalArgumentException.class, () -> {
                friendService.acceptFriendRequest(targetCode, myCode);
            });

            // then
            Mockito.verify(friendRepository, Mockito.never())
                .save(Mockito.any());
        }

        @Test
        void 실패_나에게_요청했을_경우() {
            String targetCode = "AAAAA";
            // given
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(user);

            // when
            assertThrows(IllegalArgumentException.class, () -> {
                friendService.acceptFriendRequest(targetCode, myCode);
            });

            // then
            Mockito.verify(friendRepository, Mockito.never())
                .save(Mockito.any());
        }
    }

    @Nested
    @Transactional
    class acceptFriendRequest {
        @Test
        void 성공_받은_요청을_수락함() {
            // given
            BDDMockito.given(friendRepository.findByFriendshipIsFalseAndMyCodeAndTargetCode(targetCode, myCode))
                .willReturn(friend2);
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(user);
            ConfirmFriendRequest request = new ConfirmFriendRequest(
                3L,
                true,
                targetCode
            );

            // when
            friendService.confirmFriendRequest(targetCode, myCode);

            // then
            Mockito.verify(friendRepository, Mockito.times(1))
                .save(friend2);
            Mockito.verify(friendRepository, Mockito.times(2))
                .save(any());
        }

        @Test
        void 실패_없는_유저() {
            // given
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(null);

            // when
            assertThrows(IllegalArgumentException.class, () -> friendService.confirmFriendRequest(targetCode, myCode));

            // then
            Mockito.verify(friendRepository, Mockito.never())
                .save(Mockito.any());
        }
    }

    @Nested
    @Transactional
    class rejectFriendRequest {
         @Test
        void 성공_받은_요청을_거절함() {
             // given
             BDDMockito.given(friendRepository.findByFriendshipIsFalseAndMyCodeAndTargetCode(targetCode, myCode))
                 .willReturn(friend2);
             BDDMockito.given(apiUser.getUser(targetCode))
                 .willReturn(user);

             // when
             friendService.rejectFriendRequest(targetCode, myCode);

             // then
             Mockito.verify(friendRepository, Mockito.times(1))
                 .delete(any());
        }

        @Test
        void 실패_없는_유저() {
            // given
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(null);

            // when
            assertThrows(IllegalArgumentException.class, () -> friendService.rejectFriendRequest(targetCode, myCode));

            // then
            Mockito.verify(friendRepository, Mockito.never())
                .save(Mockito.any());
        }
    }

    @Nested
    @Transactional
    class getReceivedFriendRequests {
        @Test
        void 성공_받은_요청_목록_조회_친구가_있음() {
            // given
            BDDMockito.given(friendRepository.findByFriendshipIsFalseAndTargetCode(myCode))
                .willReturn(List.of(friend2));

            // when
            friendService.getFriendRequests(myCode);

            // then
            Mockito.verify(friendRepository, Mockito.times(1))
                .findByFriendshipIsFalseAndTargetCode(myCode);
        }

        @Test
        void 성공_받은_요청_목록_조회_친구가_없음() {
            // given
            BDDMockito.given(friendRepository.findByFriendshipIsFalseAndTargetCode(myCode))
                .willReturn(List.of());

            // when
            friendService.getFriendRequests(myCode);

            // then
            Mockito.verify(friendRepository, Mockito.times(1))
                .findByFriendshipIsFalseAndTargetCode(myCode);
        }
    }

    @Nested
    @Transactional
    class deleteFriend {
        @Test
        void 성공_친구를_삭제함() {
            // given
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(user);

            // when
            friendService.removeFriend(targetCode, myCode);

            // then
            Mockito.verify(friendRepository, Mockito.times(1))
                .deleteByMyCodeAndTargetCode(myCode, targetCode);
            Mockito.verify(friendRepository, Mockito.times(1))
                .deleteByMyCodeAndTargetCode(targetCode, myCode);
        }

        @Test
        void 실패_없는_유저() {
            // given
            BDDMockito.given(apiUser.getUser(targetCode))
                .willReturn(null);

            // when
            assertThrows(IllegalArgumentException.class, () -> friendService.removeFriend(targetCode, myCode));

            // then
            Mockito.verify(friendRepository, Mockito.never())
                .save(Mockito.any());
        }
    }
}