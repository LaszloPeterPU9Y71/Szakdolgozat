package Szakdolgozat.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"users"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"users"})
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private int postalCode;

    @Column(name = "town", nullable = false)
    private String town;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "tax_number", nullable = false)
    private String taxNumber;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "companyEntity")
    private Set<UserEntity> users;
}


