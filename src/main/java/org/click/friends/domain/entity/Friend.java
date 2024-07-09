package org.click.friends.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "FRIENDS")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRIEND_ID")
    private Long friend_id;
    @Column(name = "FRIENDSHIP")
    private Boolean friendship;
    @Column(name = "USER_ID_1")
    private UUID user_id_1;
    @Column(name = "USER_ID_2")
    private UUID user_id_2;
}
