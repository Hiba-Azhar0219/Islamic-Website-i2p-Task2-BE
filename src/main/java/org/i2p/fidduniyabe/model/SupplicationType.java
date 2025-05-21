package org.i2p.fidduniyabe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Entity
@Table(name = "supplication_type")
public class SupplicationType {
    SupplicationType() {}

    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long type_id;

    @Column(name = "type")
    String type;

    @Column(name = "image_url")
    String image_url;

    public SupplicationType(long type_id, String type, String image_url) {
        this.type_id = type_id;
        this.type = type;
        this.image_url = image_url;
    }




}
