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
@Table(name = "supplication_type")
public class SupplicationType {
    @Id
    @Column(name = "supplication_type_id", length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long supplication_type_id;

    @Column(name = "supplication_type_name", length = 30)
    String supplication_type_name;

    @Column(name = "supplication_type_image_url")
    String supplication_category_image_url;
}
