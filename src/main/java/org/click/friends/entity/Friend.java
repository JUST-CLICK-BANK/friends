package org.click.friends.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private boolean friendship;
    @Column(name = "MY_CODE")
    private String myCode;
    @Column(name = "TARGET_CODE")
    private String targetCode;
}
