package org.i2p.fidduniyabe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "supplication_type")
public class SupplicationType {
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
//
//    public long type_id() {
//        return type_id;
//    }
//
//    public void settype_id(long type_id) {
//        this.type_id = type_id;
//    }
//
//    public String gettype() {
//        return type;
//    }
//
//    public void settype(String type) {
//        this.type = type;
//    }
//
//    public String getImage_url() {
//        return image_url;
//    }
//
//    public void setImage_url(String image_url) {
//        this.image_url = image_url;
//    }

}
