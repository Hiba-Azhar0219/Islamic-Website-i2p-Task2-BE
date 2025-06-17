package org.i2p.fidduniyabe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "supplication_type")
public class SupplicationType {
   public SupplicationType() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private long typeId;

   @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    String name;

   @NotBlank

   @Column(name = "image_url", nullable = false)
    String imageUrl;

    @Column(name = "is_active")
    boolean isActive = true;


    public void setIsActive(boolean isActive) {
        this.isActive = isActive;}

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
