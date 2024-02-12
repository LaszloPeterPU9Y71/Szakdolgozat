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
    @Column(name = "ownerCompanyId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "ownerCompanyName", nullable = false)
    private String companyName;

    @Column(name = "ownerCompanyPostalCode", nullable = false)
    private int postalCode;

    @Column(name = "ownerCompanyTown", nullable = false)
    private String town;

    @Column(name = "ownerCompanyStreet", nullable = false)
    private String street;

    @Column(name = "ownerCompanyTaxNumber", nullable = false, unique = true)
    private String taxNumber;

    @Column(name = "ownerCompanyAccountNumber", nullable = true)
    private String accountNumber;

    @Column(name = "ownerCompanyStatus", nullable = false)
    private Boolean status;


    @OneToMany(mappedBy = "ownerCompanyEntity")
    private Set<OwnerCompanyEmloyeeEntity> employee;
}
