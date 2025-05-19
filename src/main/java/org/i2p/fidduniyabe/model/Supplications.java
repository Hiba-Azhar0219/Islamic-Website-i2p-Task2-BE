package org.i2p.fidduniyabe.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplications")
public class Supplications{

    @Id
    @Column(name = "supplication_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   long supplication_id;

    @Column(name = "supplication_type_id")
    String supplication_type_id;

    @Column(name = "supplication_arabic", columnDefinition="text")
    String supplication_arabic;

    @Column(name = "supplication_english",columnDefinition = "text")
     String supplication_english;

    @Column(name = "supplication_roman",columnDefinition = "text")
    String supplication_roman;

    @Column(name = "supplication_reference_type",columnDefinition = "text")
    String supplication_reference_type;

    @Column(name = "supplication_reference_text",columnDefinition = "text")
    String supplication_reference_text;

    @Column(name = "supplication_reference_chapter",columnDefinition = "text")
    String supplication_reference_chapter;




}
