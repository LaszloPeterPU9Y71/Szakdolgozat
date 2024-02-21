package Szakdolgozat.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"employee"})


public class OwnerCompanyEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_company_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "owner_company_name", nullable = false)
    private String companyName;

    @Column(name = "owner_company_postal_code", nullable = false)
    private int postalCode;

    @Column(name = "owner_company_town", nullable = false)
    private String town;

    @Column(name = "owner_company_street", nullable = false)
    private String street;

    @Column(name = "owner_company_tax_number", nullable = false, unique = true)
    private String taxNumber;

    @Column(name = "owner_company_account_number", nullable = true)
    private String accountNumber;

    @Column(name = "owner_company_status", nullable = false)
    private Boolean status;


    @OneToMany(mappedBy = "ownerCompanyEntity")
    private Set<OwnerCompanyEmployeeEntity> employee;
}
