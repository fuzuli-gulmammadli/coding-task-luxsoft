package az.fuzuli.gulmammadli.codingtask.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="app_data")
public class AppData {
    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime updatedTimestamp;
}
