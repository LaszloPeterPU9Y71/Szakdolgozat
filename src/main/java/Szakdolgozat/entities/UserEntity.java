package Szakdolgozat.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "user_entity")

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_tel_num", nullable = false)
    private String telNum;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_title", nullable = false)
    private String title;

    @Column(name = "user_status", nullable = false)
    private boolean status;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_company_id",
            foreignKey = @ForeignKey(name = "FK_User_Company"))
    private CompanyEntity companyEntity;


    @ManyToMany
    @JoinTable(name = "user_tool",
        joinColumns = @JoinColumn(name = "user_id"),
        foreignKey = @ForeignKey(name = "FK_User_Tool"),
        inverseJoinColumns = @JoinColumn(name = "tool_id"),
        inverseForeignKey = @ForeignKey(name = "FK_Tool_User")
    )
    private List<ToolEntity> toolEntity;



}
