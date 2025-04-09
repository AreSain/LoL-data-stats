package aresain.loldatastats.riot;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RiotClientConfig {

	@Value("${feign.riot.api.key}")
	private String apiKey;

	@Bean
	public RequestInterceptor apiKeyInterceptor() {
		return template -> template.query("api_key", apiKey);
	}
}
