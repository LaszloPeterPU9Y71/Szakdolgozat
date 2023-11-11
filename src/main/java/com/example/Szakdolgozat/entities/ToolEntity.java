package com.example.Szakdolgozat.entities;

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
@JsonIgnoreProperties(value = {"ownerCompanyEmloyeeEntity"})

public class ToolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toolId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "toolName", nullable = false)
    private String name;

    @Column(name = "toolTypeNumber", nullable = false)
    private String typeNumber;

    @Column(name = "toolItemNumber", nullable = false)
    private String itemNumber;

    @Column(name = "toolSerialNumber", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "toolDateOfRecieving", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private LocalDateTime dateOfReceiving;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ToolDefect",
        joinColumns = @JoinColumn(name = "toolId"),
            foreignKey = @ForeignKey(name = "FK_Tool_Defect"),
        inverseJoinColumns = @JoinColumn(name = "defectId"),
            inverseForeignKey = @ForeignKey(name = "FK_Defect_Tool")
    )
    private List<DefectEntity> defects;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ToolEmployeeId",
            foreignKey = @ForeignKey(name = "FK_Tool_Employee"))
    private OwnerCompanyEmloyeeEntity ownerCompanyEmloyeeEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ToolSpareparts",
            joinColumns = @JoinColumn(name = "toolId"),
            foreignKey = @ForeignKey(name = "FK_Tool_SpareParts"),
            inverseJoinColumns = @JoinColumn(name = "sparePartsId"),
            inverseForeignKey = @ForeignKey(name = "FK_SpareParts_Tool"))
    private List<SparePartsEntity> spareparts;
}
