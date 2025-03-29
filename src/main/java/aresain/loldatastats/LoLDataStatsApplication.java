package aresain.loldatastats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LoLDataStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoLDataStatsApplication.class, args);
	}

}
