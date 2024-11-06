package tpe.microservicioadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    private Long id;
    private String name;
    private String location;
    private boolean isActive;

}
