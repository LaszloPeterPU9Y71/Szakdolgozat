package Szakdolgozat.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolDto {
    private long id;
    private String name;
    private String typeNumber;
    private String itemNumber;
    private String serialNumber;
    private LocalDateTime dateOfReceiving;
    private String status;
}
