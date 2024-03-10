package Szakdolgozat.entities;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class DefectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "defect_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "defect_name", nullable = false)
    private String name;

    @Column(name = "defect_description", nullable = true)
    private String descrition;

}
