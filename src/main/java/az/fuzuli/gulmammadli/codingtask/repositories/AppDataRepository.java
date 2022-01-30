package az.fuzuli.gulmammadli.codingtask.repositories;

import az.fuzuli.gulmammadli.codingtask.entities.AppData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppDataRepository extends JpaRepository<AppData, String> {
}
