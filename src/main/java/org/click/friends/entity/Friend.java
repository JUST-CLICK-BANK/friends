package org.click.friends.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Long friendId;
    @Setter
    @Column(name = "FRIENDSHIP")
    private Boolean friendship;
    @Column(name = "MY_ID")
    private UUID myId;
    @Column(name = "TARGET_ID")
    private UUID targetId;
}
