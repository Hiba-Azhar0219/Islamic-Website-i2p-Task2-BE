package org.i2p.fidduniyabe.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "supplications")
public class Supplications{

    public Supplications() {}
    @Id
    @Column(name = "supplication_id", nullable = false, updatable = false, unique = true )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   long supplicationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplication_type_id")
    @JsonBackReference
    private SupplicationType supplicationTypeID;

    @Column(name = "sup_id_in_csv")
    int supIdInCsv;

    @Column(name = "arabic", columnDefinition="text", nullable = false,unique=true)
    @NotBlank(message = "Arabic text is required")

    String arabic;

    @Column(name = "english",columnDefinition = "text",nullable = false, unique = true)
    @NotBlank(message = "English text is required")
     String english;

    @Column(name = "roman",columnDefinition = "text",nullable = false, unique = true)
    @NotBlank(message = "Roman text is required")

    String roman;

   @Column(name = "heading", columnDefinition="text")
    String heading;


    @Column(name = "total_count")
    int totalCount;


    @Column(name = "num_sub_categories")
    int numSubCategories;


    @Column(name = "sub_heading", columnDefinition ="text")
    String subHeading;

    @Column(name = "num_of_duas")
    int numOfDuas;

    @Column(name = "ref_type",columnDefinition = "text")
    String refType;

    @Column(name = "ref_text",columnDefinition = "text")
    String refText;

    @Column(name = "ref_chapter",columnDefinition = "text")
    String refChapter;

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
