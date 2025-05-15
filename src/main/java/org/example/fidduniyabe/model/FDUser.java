package org.example.fidduniyabe.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fd_user")
public class FDUser {
    @Id
    @Column(name = "fd_user_id", length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long fd_user_id;


    @Column(name = "fd_user_password", length = 10)
    private String fd_user_password;

    @Column(name = "fd_isAdmin", columnDefinition = "boolean")
    boolean fd_isAdmin;
}
