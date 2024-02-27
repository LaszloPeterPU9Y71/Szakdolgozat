package Szakdolgozat.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OwnerCompanyEmployeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_company_employee_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "owner_company_employee_name", nullable = false)
    private String name;

    @Column(name = "owner_company_employee_tel_num", nullable = false)
    private String telNum;

    @Column(name = "owner_company_employee_email", nullable = false, unique = true)
    private String email;

    @Column(name = "owner_company_employee_title", nullable = false)
    private String title;

    @Column(name = "owner_company_employee_status", nullable = false)
    private boolean status;


    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_company_employee",
            foreignKey = @ForeignKey(name = "FK_Owner_company_employee"))
    private OwnerCompanyEntity ownerCompanyEntity;



}
