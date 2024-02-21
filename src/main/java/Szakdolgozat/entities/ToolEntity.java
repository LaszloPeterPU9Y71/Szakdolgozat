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
    @Column(name = "tool_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "tool_name", nullable = false)
    private String name;

    @Column(name = "tool_type_number", nullable = false)
    private String typeNumber;

    @Column(name = "tool_item_number", nullable = false)
    private String itemNumber;

    @Column(name = "tool_serial_number", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "tool_date_of_recieving", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private LocalDateTime dateOfReceiving;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tool_defect",
        joinColumns = @JoinColumn(name = "tool_id"),
            foreignKey = @ForeignKey(name = "FK_Tool_Defect"),
        inverseJoinColumns = @JoinColumn(name = "defect_id"),
            inverseForeignKey = @ForeignKey(name = "FK_Defect_Tool")
    )
    private List<DefectEntity> defects;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tool_employee_id",
            foreignKey = @ForeignKey(name = "FK_Tool_Employee"))
    private OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tool_spareparts",
            joinColumns = @JoinColumn(name = "toolId"),
            foreignKey = @ForeignKey(name = "FK_Tool_spareParts"),
            inverseJoinColumns = @JoinColumn(name = "spare_parts_id"),
            inverseForeignKey = @ForeignKey(name = "FK_SpareParts_Tool"))
    private List<SparePartsEntity> spareparts;
}
