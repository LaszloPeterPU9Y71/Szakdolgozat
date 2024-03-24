package Szakdolgozat.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"owner_company_emloyee_entity"})

public class ToolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type_number")
    private String typeNumber;

    @Column(name = "item_number")
    private String itemNumber;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "date_of_recieving")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateOfReceiving;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "description")
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tool_defect",
        joinColumns = @JoinColumn(name = "tool_id"),
            foreignKey = @ForeignKey(name = "FK_Tool_Defect"),
        inverseJoinColumns = @JoinColumn(name = "defect_id"),
            inverseForeignKey = @ForeignKey(name = "FK_Defect_Tool")
    )
    private List<DefectEntity> defects;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id",
            foreignKey = @ForeignKey(name = "FK_Tool_Employee"))
    private OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tool_spareparts",
            joinColumns = @JoinColumn(name = "tool_id"),
            foreignKey = @ForeignKey(name = "FK_Tool_spareParts"),
            inverseJoinColumns = @JoinColumn(name = "spare_parts_id"),
            inverseForeignKey = @ForeignKey(name = "FK_SpareParts_Tool"))
    private List<SparePartsEntity> spareparts;
}
