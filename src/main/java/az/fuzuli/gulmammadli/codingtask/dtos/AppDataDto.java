package az.fuzuli.gulmammadli.codingtask.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppDataDto {
    private String id;
    private String name;
    private String description;
    private String updatedTimestamp;
}
