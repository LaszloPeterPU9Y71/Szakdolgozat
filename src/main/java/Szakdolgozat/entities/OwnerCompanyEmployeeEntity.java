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
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tel_num", nullable = false)
    private String telNum;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "status", nullable = false)
    private Boolean status;



    @ManyToOne()
    @JoinColumn(name = "owner_company_id",
            foreignKey = @ForeignKey(name = "FK_Owner_company_id"))
    private OwnerCompanyEntity ownerCompanyEntity;

}
