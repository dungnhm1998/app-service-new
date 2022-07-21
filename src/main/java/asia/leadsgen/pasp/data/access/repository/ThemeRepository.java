package asia.leadsgen.pasp.data.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asia.leadsgen.pasp.entity.ThemeEntity;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, String>{

}
