package aresain.loldatastats.riot;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RiotClientConfig {

	@Value("${feign.riot.api.key}")
	private String apiKey;

	@Bean
	public RequestInterceptor apiKeyInterceptor() {
		return template -> template.query("api_key", apiKey);
	}

	@Bean
	public Decoder feignDecoder() {
		return new JacksonDecoder(customObjectMapper());
	}

	@Bean
	public Encoder feignEncoder() {
		return new JacksonEncoder(customObjectMapper());
	}

	private ObjectMapper customObjectMapper() {
		return new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.registerModule(new JavaTimeModule());
	}
}
