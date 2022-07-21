package asia.leadsgen.pasp.data.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import asia.leadsgen.pasp.entity.ThemeSettingEntity;

public interface ThemeSettingRepository extends JpaRepository<ThemeSettingEntity, String>{

	ThemeSettingEntity findByTypeAndThemeIdAndState(String type, String themeId, String state);
}
