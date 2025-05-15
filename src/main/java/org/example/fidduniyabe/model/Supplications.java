package org.example.fidduniyabe.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "supplications")
public class Supplications{

    @Id
    @Column(name = "supplication_id", length = 30, nullable = false)
    private String supplication_id;

    @Column(name = "supplication_type", length = 30, nullable = false)
    private String supplication_type;

    @Column(name = "supplication_arabic", columnDefinition="text",  nullable = false)
    private String supplication_arabic;

    @Column(name = "supplication_english",columnDefinition = "text",  nullable = false)
    private String supplication_english;


}
