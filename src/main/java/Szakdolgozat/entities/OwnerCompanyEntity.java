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
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "name", nullable = false)
    private String companyName;

    @Column(name = "postal_code", nullable = false)
    private int postalCode;

    @Column(name = "town", nullable = false)
    private String town;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "tax_number", nullable = false, unique = true)
    private String taxNumber;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "status", nullable = false)
    private Boolean status;


    @OneToMany(mappedBy = "ownerCompanyEntity")
    private Set<OwnerCompanyEmployeeEntity> employee;
}
