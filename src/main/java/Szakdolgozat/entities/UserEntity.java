package Szakdolgozat.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "user_entity")

public class UserEntity {

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

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "status", nullable = false)
    private Boolean status;


    @ManyToOne()
    @JoinColumn(name = "company_id",
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telNum='" + telNum + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", companyEntity=" + companyEntity +
                ", toolEntity=" + toolEntity +
                '}';
    }
}
