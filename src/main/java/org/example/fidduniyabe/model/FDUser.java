package org.example.fidduniyabe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "fd_user")
public class FDUser {
    @Id
    @Column(name = "fd_user_id", length = 40)
    int fd_user_id;


    @Column(name = "fd_user_password", length = 10)
    String fd_user_password;

    @Column(name = "fd_isAdmin", columnDefinition = "boolean")
    boolean fd_isAdmin;
}
