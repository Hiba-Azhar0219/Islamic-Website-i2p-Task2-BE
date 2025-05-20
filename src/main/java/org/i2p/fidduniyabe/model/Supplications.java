package org.i2p.fidduniyabe.model;
import jakarta.persistence.*;


@Entity
@Table(name = "supplications")
public class Supplications{

    @Id
    @Column(name = "supplication_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   long supplication_id;

    //this will be the forign key
//    @Column(name = "type_id")
//    String type_id;

    @Column(name = "sup_id_in_csv")
    String sup_id_in_csv;

    @Column(name = "arabic", columnDefinition="text")
    String arabic;

    @Column(name = "english",columnDefinition = "text")
     String english;

    @Column(name = "roman",columnDefinition = "text")
    String roman;

    @Column(name = "urdu",columnDefinition = "text")
    String urdu;

    @Column(name = "reference_type",columnDefinition = "text")
    String reference_type;

    @Column(name = "reference_text",columnDefinition = "text")
    String reference_text;

    @Column(name = "reference_chapter",columnDefinition = "text")
    String reference_chapter;


    public Supplications(long supplication_id, String sup_id_in_csv, String arabic, String english, String roman, String urdu, String reference_type, String reference_chapter, String reference_text) {
        this.supplication_id = supplication_id;
        this.sup_id_in_csv = sup_id_in_csv;
        this.arabic = arabic;
        this.english = english;
        this.roman = roman;
        this.urdu = urdu;
        this.reference_type = reference_type;
        this.reference_chapter = reference_chapter;
        this.reference_text = reference_text;
    }
}
