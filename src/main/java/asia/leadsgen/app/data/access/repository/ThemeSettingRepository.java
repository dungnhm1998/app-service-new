package asia.leadsgen.app.data.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import asia.leadsgen.app.entity.ThemeSettingEntity;

public interface ThemeSettingRepository extends JpaRepository<ThemeSettingEntity, String>{

	ThemeSettingEntity findByTypeAndThemeIdAndState(String type, String themeId, String state);
}
