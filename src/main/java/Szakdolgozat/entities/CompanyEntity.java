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
    @Column(name = "company_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "company_name", nullable = false)
    private String name;

    @Column(name = "company_postal_code", nullable = false)
    private int postalCode;

    @Column(name = "company_town", nullable = false)
    private String town;

    @Column(name = "company_street", nullable = false)
    private String street;

    @Column(name = "company_tax_number", nullable = false)
    private String taxNumber;

    @Column(name = "company_status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "companyEntity")
    private Set<UserEntity> users;
}


