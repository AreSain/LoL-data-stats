package aresain.loldatastats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients
@SpringBootApplication
@EnableJpaAuditing
public class LoLDataStatsApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoLDataStatsApplication.class, args);
	}
}
