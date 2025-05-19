package org.i2p.fidduniyabe.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Entity

@Table(name = "fd_user")
public class FDUser {
    @Id
    @Column(name = "fd_user_email", unique = true)
    private String fd_user_email;

    @Column(name = "fd_user_name")
    private String fd_user_name;

    @Column(name = "fd_user_password")
    private String fd_user_password;

    @Column(name = "fd_isAdmin", columnDefinition = "boolean default false")
    boolean fd_isAdmin;

    public boolean isFd_isAdmin() {
        return fd_isAdmin;
    }

    public void setFd_isAdmin(boolean fd_isAdmin) {
        this.fd_isAdmin = fd_isAdmin;
    }

    public String getFd_user_password() {
        return fd_user_password;
    }

    public void setFd_user_password(String fd_user_password) {
        this.fd_user_password = fd_user_password;
    }

    public String getFd_user_name() {
        return fd_user_name;
    }

    public void setFd_user_name(String fd_user_name) {
        this.fd_user_name = fd_user_name;
    }

    public String getFd_user_email() {
        return fd_user_email;
    }

    public void setFd_user_email(String fd_user_email) {
        this.fd_user_email = fd_user_email;
    }

//    public FDUser(String name, String email, String password) {
//        this.fd_user_name = name;
//        this.fd_user_email = email;
//        this.fd_user_password = password;
//    }
}
