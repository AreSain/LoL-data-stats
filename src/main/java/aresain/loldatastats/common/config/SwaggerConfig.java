package aresain.loldatastats.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi RiotDataGroup() {
		return GroupedOpenApi.builder()
			.group("Riot API")
			.pathsToMatch("/**")
			.packagesToScan("aresain.loldatastats")
			.build();
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("LOLDataStats API")
				.version("v1.0"));
	}
}
