package asia.leadsgen.pasp.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import asia.leadsgen.pasp.data.access.repository.ThemeSettingRepository;
import asia.leadsgen.pasp.data.access.repository.ThemeTemplateRepository;
import asia.leadsgen.pasp.entity.ThemeSettingEntity;
import asia.leadsgen.pasp.entity.ThemeTemplateEntity;
import asia.leadsgen.pasp.model.ResultModel;
import asia.leadsgen.pasp.model.request.SettingModel;
import asia.leadsgen.pasp.model.request.ThemeRequest;

@Service
public class ThemeSettingService {

	@Autowired
	ThemeSettingRepository themeSettingRepository;

	@Autowired
	ThemeTemplateRepository themeTemplateRepository;

	public ResultModel update(ThemeRequest requset) {

		ThemeSettingEntity response = new ThemeSettingEntity();

		StringTemplateResolver templateResolver = new StringTemplateResolver();
		templateResolver.setOrder(1);
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		// TODO Cacheable or Not ?
		templateResolver.setCacheable(false);
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		Context templateContext = new Context();

		if ("style".equals(requset.getType())) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(":root { \n");
			for (SettingModel settingModel : requset.getSettings()) {
				stringBuilder.append("--" + settingModel.getId() + ": " + check(settingModel.getValue()) + "\n");
			}
			stringBuilder.append("}");
			System.out.println(stringBuilder.toString());

			ThemeSettingEntity themeSettingEntity = themeSettingRepository
					.findByTypeAndThemeIdAndState(requset.getType(), requset.getThemeId(), "approved");

			themeSettingEntity.setValue(stringBuilder.toString());

			themeSettingRepository.save(themeSettingEntity);

			response = themeSettingRepository.findByTypeAndThemeIdAndState(requset.getType(), requset.getThemeId(),
					"approved");
		} else if ("html".equals(requset.getType())) {
			ThemeTemplateEntity themeTemplateEntity = themeTemplateRepository
					.findByTypeAndState(requset.getTemplateId(), "approved");

			for (SettingModel settingModel : requset.getSettings()) {
				String value = settingModel.getValue();
				if ("header_navigation_items".equals(settingModel.getId())) {
					value = themeTemplateRepository.findByIdAndState(settingModel.getValue(), "approved").getContent();
				}
				if("fixed_header".equals(settingModel.getId())) {
					System.out.println(settingModel.getId());
					if("true".equals(settingModel.getValue())) {
						value = "class=\"sticky-top\"";
					}
					System.out.println(value);
				}
				if("header_logo_url".equals(settingModel.getId())) {
					if(StringUtils.isEmpty(value)) {
						value = "<i class=\"bi bi-reddit h3\"></i>";
					}else {
						value = "<img class=\"img-fluid\" src=\""+value+"\"/>";
					}
				}
				templateContext.setVariable(settingModel.getId(), value);
			}
			String value = templateEngine.process(themeTemplateEntity.getContent(), templateContext);
			value = value.replaceAll("&lt;", "<");
			value = value.replaceAll("&gt;", ">");
			value = value.replaceAll("&quot;", "\"");

			ThemeSettingEntity themeSettingEntity = themeSettingRepository
					.findByTypeAndThemeIdAndState(requset.getTemplateId(), requset.getThemeId(), "approved");

			themeSettingEntity.setValue(value);

			themeSettingRepository.save(themeSettingEntity);

			response = themeSettingRepository.findByTypeAndThemeIdAndState(requset.getTemplateId(),
					requset.getThemeId(), "approved");
		}

//		StringTemplateResolver templateResolver = new StringTemplateResolver();
//		templateResolver.setOrder(1);
//		templateResolver.setTemplateMode(TemplateMode.TEXT);
//		// TODO Cacheable or Not ?
//		templateResolver.setCacheable(false);
//		TemplateEngine templateEngine = new TemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver);
//
//		Context themeContext = new Context();
//		
//		for(SettingModel settingModel : requset.getSettings()) {
//			switch (settingModel.getId()) {
//				case "heading_font":
//					System.out.println(settingModel.getValue() + " + " + font(settingModel.getValue()));
//					themeContext.setVariable("heading_font", font(settingModel.getValue()));
//					break;
//
//				case "heading_variant":
//					themeContext.setVariable("heading_variant", variant(settingModel.getValue()));
//					break;
//					
//				case "heading_base_size":
//					themeContext.setVariable("heading_base_size", settingModel.getValue());
//					break;
//					
//				case "heading_active_capitalize":
//					themeContext.setVariable("heading_active_capitalize", capitalize(settingModel.getValue()));
//					break;
//					
//				case "body_font":
//					System.out.println(settingModel.getValue() + " + " + font(settingModel.getValue()));
//					themeContext.setVariable("body_font", font(settingModel.getValue()));
//					break;
//					
//				case "body_variant":
//					themeContext.setVariable("body_variant", variant(settingModel.getValue()));
//					break;
//					
//				case "body_base_size":
//					themeContext.setVariable("body_base_size", settingModel.getValue());
//					break;
//					
//				case "btn_link_font":
//					System.out.println(settingModel.getValue() + " + " + font(settingModel.getValue()));
//					themeContext.setVariable("btn_link_font", font(settingModel.getValue()));
//					break;
//					
//				case "btn_link_variant":
//					themeContext.setVariable("btn_link_variant", variant(settingModel.getValue()));
//					break;
//					
//				case "btn_link_base_size":
//					themeContext.setVariable("btn_link_base_size", settingModel.getValue());
//					break;
//					
//				case "btn_link_active_capitalize":
//					themeContext.setVariable("btn_link_active_capitalize", capitalize(settingModel.getValue()));
//					break;
//				default:
//					break;
//			}
//		}
//
//		String value = templateEngine.process(themeTemplateEntity.getContent(), themeContext);

		ResultModel resultModel = new ResultModel(200, HttpStatus.OK.getReasonPhrase(), response, null);

//		System.out.println(value);
		return resultModel;
	}

	public String font(String key) {
		String result = null;
		switch (key) {
			case "_roboto":
				result = "Roboto";
				break;

			case "time_new_roman":
				result = "Time new Roman";
				break;

			case "arial":
				result = "Arial";
				break;

			default:
				break;
		}
		return result;
	}

	public String capitalize(String key) {
		String result = null;
		switch (key) {
			case "true":
				result = "capitalize";
				break;

			case "false":
				result = "uppercase";
				break;

			default:
				break;
		}
		return result;
	}

	public String variant(String key) {
		String result = null;
		switch (key) {
			case "_italic":
				result = "italic";
				break;

			case "_bold":
				result = "bold";
				break;

			case "_underline":
				result = "underline";
				break;

			case "_normal":
				result = "normal";
				break;

			default:
				break;
		}
		return result;
	}

	public String check(String key) {
		String result = key;
		switch (key) {
			case "_italic":
				result = "italic";
				break;

			case "_bold":
				result = "bold";
				break;

			case "_underline":
				result = "underline";
				break;

			case "_normal":
				result = "normal";
				break;

			case "true":
				result = "capitalize";
				break;

			case "false":
				result = "uppercase";
				break;

			case "_roboto":
				result = "Roboto";
				break;

			case "time_new_roman":
				result = "Time new Roman";
				break;

			case "arial":
				result = "Arial";
				break;

			default:
				break;
		}
		return result;
	}

//	public ResultModel update(ThemeRequest requset) {
//
//		String themeId = requset.getThemeId();
//		String type = requset.getType();
//
//		List<PropertyModel> propertyObj = requset.getComponent().getProperties();
//
//		String compType = propertyObj.get(0).getId();
//		
//		System.out.println(compType);
//
//		ThemeSettingEntity themeSettingEntity = themeSettingRepository.findByTypeAndThemeIdAndState(compType, themeId,
//				"approved");
//
//		String menuId = null;
//		String content = null;
//		String textColor = null;
//		String backgroundColor = null;
//		String headingFont = null;
//		String headingVariant = null;
//		String headingBaseSize = null;
//		String headingActiveCapitalize = null;
//		String bodyFont = null;
//		String bodyVariant = null;
//		String bodyBaseSize = null;
//		String btnLinkFont = null;
//		String btnLinkVariant = null;
//		String btnLinkBaseSize = null;
//		String btnLinkActiveCapitalize = null;
//		for (PropertyModel propertyModel : propertyObj) {
//			if (propertyModel.getId().equals("header_navigation")) {
//				for (ChildrenModel childrenModel : propertyModel.getChildren()) {
//					if (childrenModel.getId().equals("header_navigation_items")) {
//						if ("html".equals(type)) {
//							menuId = childrenModel.getData().get(0).getValue();
//						}
//					}
//				}
//			}
//			if (propertyModel.getId().equals("announcement_config")) {
//				for (ChildrenModel childrenModel : propertyModel.getChildren()) {
//					if ("html".equals(type)) {
//						if (childrenModel.getId().equals("announcement_content")) {
//							content = childrenModel.getData().get(0).getValue();
//						}
//						if (childrenModel.getId().equals("announcement_text_color")) {
//							textColor = childrenModel.getData().get(0).getValue();
//						}
//						if (childrenModel.getId().equals("announcement_background")) {
//							backgroundColor = childrenModel.getData().get(0).getValue();
//						}
//					}
//
//				}
//			}
//			if (propertyModel.getId().equals("typography_heading")) {
//				for (ChildrenModel childrenModel : propertyModel.getChildren()) {
//					if ("style".equals(type)) {
//						if (childrenModel.getId().equals("heading_config")) {
//							for (ValueModel valueModel : childrenModel.getData()) {
//								if (valueModel.getId().equals("heading_font")) {
//									headingFont = valueModel.getValue();
//								}
//								if (valueModel.getId().equals("heading_variant")) {
//									headingVariant = variant(valueModel.getValue());
//								}
//								if (valueModel.getId().equals("heading_base_size")) {
//									headingBaseSize = valueModel.getValue();
//								}
//								if (valueModel.getId().equals("heading_active_capitalize")) {
//									headingActiveCapitalize = activeCapitalize(valueModel.getValue());
//								}
//							}
//						}
//					}
//				}
//			}
//			if (propertyModel.getId().equals("typography_body_text")) {
//				for (ChildrenModel childrenModel : propertyModel.getChildren()) {
//					if ("style".equals(type)) {
//						if (childrenModel.getId().equals("body_config")) {
//							for (ValueModel valueModel : childrenModel.getData()) {
//								if (valueModel.getId().equals("body_font")) {
//									bodyFont = valueModel.getValue();
//								}
//								if (valueModel.getId().equals("body_variant")) {
//									bodyVariant = variant(valueModel.getValue());
//								}
//								if (valueModel.getId().equals("body_base_size")) {
//									bodyBaseSize = valueModel.getValue();
//								}
//							}
//						}
//					}
//				}
//			}
//			if (propertyModel.getId().equals("typography_button_link")) {
//				for (ChildrenModel childrenModel : propertyModel.getChildren()) {
//					if ("style".equals(type)) {
//						if (childrenModel.getId().equals("btn_link_config")) {
//							for (ValueModel valueModel : childrenModel.getData()) {
//								if (valueModel.getId().equals("btn_link_font")) {
//									btnLinkFont = valueModel.getValue();
//								}
//								if (valueModel.getId().equals("btn_link_variant")) {
//									btnLinkVariant = variant(valueModel.getValue());
//								}
//								if (valueModel.getId().equals("btn_link_base_size")) {
//									btnLinkBaseSize = valueModel.getValue();
//								}
//								if (valueModel.getId().equals("btn_link_active_capitalize")) {
////									btnLinkActiveCapitalize = valueModel.getValue();
//									btnLinkActiveCapitalize = activeCapitalize(valueModel.getValue());
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		if (StringUtils.isNotEmpty(menuId)) {
//			ThemeTemplateEntity header = themeTemplateRepository.findByIdAndStateAndType("ojrKfbgVzEYPiEqE", "approved",
//					"header");
//			ThemeTemplateEntity menu = themeTemplateRepository.findByIdAndStateAndType(menuId, "approved", "menu");
//			ThemeTemplateEntity headerIcon = themeTemplateRepository.findByIdAndStateAndType("H9NZznFxOIT6qUIB",
//					"approved", "header-icons");
//
//			StringBuilder builder = new StringBuilder();
//			builder.append(header.getContent());
//			builder.append(menu.getContent());
//			builder.append(headerIcon.getContent());
//			builder.append("</header>");
//
//			themeSettingEntity.setValue(builder.toString());
//			themeSettingEntity.setComponentId(compType);
//		}
//
////		ThemeTemplateEntity org = themeTemplateRepository.findByIdAndStateAndType("ayuV8v5ulSDsuLmT", "approved",
////				"announcement-bar");
//
//		ThemeTemplateEntity typographyHeading = themeTemplateRepository.findByIdAndStateAndType("LLXcJaEj4P-3OGgK",
//				"approved", "typography_heading");
//
////		System.out.println(typographyHeading);
//		
//		StringTemplateResolver templateResolver = new StringTemplateResolver();
//		templateResolver.setOrder(1);
//		templateResolver.setTemplateMode(TemplateMode.TEXT);
//		// TODO Cacheable or Not ?
//		templateResolver.setCacheable(false);
//		TemplateEngine templateEngine = new TemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver);
//
//		Context mailContext = new Context();
//
////		mailContext.setVariable("announcement_bg_value", backgroundColor);
////		mailContext.setVariable("announcement_color_value", textColor);
////		mailContext.setVariable("announcement_text_value", content);
////
////		String value = templateEngine.process(org.getContent(), mailContext);
//
////		themeSettingEntity.setValue(value);
////		themeSettingEntity.setComponentId(compType);
//
//		System.out.println(headingFont);
//		mailContext.setVariable("heading_font", headingFont);
//		mailContext.setVariable("heading_size", headingBaseSize);
//		mailContext.setVariable("heading_base_size", headingVariant);
//		mailContext.setVariable("heading_capitalize", headingActiveCapitalize);
//		mailContext.setVariable("body_font", bodyFont);
//		mailContext.setVariable("body_size", bodyBaseSize);
//		mailContext.setVariable("body_base_size", bodyVariant);
//		mailContext.setVariable("btn_link_font", btnLinkFont);
//		mailContext.setVariable("btn_link_size", btnLinkBaseSize);
//		mailContext.setVariable("btn_link_base_size", btnLinkVariant);
//		mailContext.setVariable("btn_link_capitalize", btnLinkActiveCapitalize);
//
//		String value = templateEngine.process(typographyHeading.getContent(), mailContext);
//
//		themeSettingEntity.setValue(value);
//		themeSettingEntity.setComponentId(compType);
//
//		List<ThemeSettingEntity> themeSettingEntities = new ArrayList<ThemeSettingEntity>();
//
//		themeSettingRepository.save(themeSettingEntity);
//
//		themeSettingEntity = themeSettingRepository.findByTypeAndThemeIdAndState(compType, themeId, "approved");
//
//		themeSettingEntities.add(themeSettingEntity);
//
//		ThemeResponse response = new ThemeResponse(themeId, themeSettingEntities);
//
//		ResultModel resultModel = new ResultModel(200, HttpStatus.OK.getReasonPhrase(), response, null);
//		return resultModel;
//	}
//
////	public void Comp 
//
//	public void checkComponent(List<PropertyModel> propertyModels) {
//		for (PropertyModel propertyModel : propertyModels) {
//			if (propertyModel.getId().equals("header_navigation")) {
//				for (ChildrenModel childrenModel : propertyModel.getChildren()) {
//					if (childrenModel.getId().equals("header_navigation_items")) {
////						menuId = childrenModel.getValue();
//					}
//				}
//			}
//
//			if (propertyModel.getId().equals("announcement_config")) {
//				for (ChildrenModel childrenModel : propertyModel.getChildren()) {
//					if (childrenModel.getId().equals("announcement_content")) {
////						content = childrenModel.getValue();
//					}
//					if (childrenModel.getId().equals("announcement_text_color")) {
////						textColor = childrenModel.getValue();
//					}
//					if (childrenModel.getId().equals("announcement_background")) {
////						backgroundColor = childrenModel.getValue();
//					}
//				}
//			}
//		}
//	}
//
//	public String variant(String key) {
//		switch (key) {
//			case "underline":
//				key = "text-decoration: underline";
//				break;
//			case "italic":
//				key = "font-style: italic";
//				break;
//			case "bold":
//				key = "font-weight: bold";
//				break;
//		}
//		return key;
//	}
//
//	public String activeCapitalize(String value) {
//		if ("true".equals(value)) {
//			value = "capitalize";
//		} else {
//			value = "uppercase";
//		}
//		return value;
//	}
//
//	public void headerNavigation() {
//
//	}
//
//	public void announcementConfig() {
//
//	}

}
