package org.example.fidduniyabe.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User{

    @Id
    @Column(name = "user_id", length = 40, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(name = "user_password", columnDefinition="varchar",length = 10,  nullable = false)
    private String user_password;

    @Column(name = "user_type", columnDefinition="boolean",length = 10,  nullable = false)
    private boolean user_type;

}