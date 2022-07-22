package asia.leadsgen.app.data.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asia.leadsgen.app.entity.ThemeTemplateEntity;

@Repository
public interface ThemeTemplateRepository extends JpaRepository<ThemeTemplateEntity, String>{
	
	ThemeTemplateEntity findByIdAndStateAndType(String id, String state, String type);
	
	ThemeTemplateEntity findByTypeAndState(String type, String state);
	
	ThemeTemplateEntity findByIdAndState(String id, String state);
}
