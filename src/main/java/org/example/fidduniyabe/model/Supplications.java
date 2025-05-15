package org.example.fidduniyabe.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "supplications")
public class Supplications{

    @Id
    @Column(name = "supplication_id", length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   long supplication_id;

    @Column(name = "supplication_type", length = 30)
 String supplication_type;

    @Column(name = "supplication_arabic", columnDefinition="text")
  String supplication_arabic;

    @Column(name = "supplication_english",columnDefinition = "text")
  String supplication_english;


}
