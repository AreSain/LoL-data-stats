package aresain.loldatastats;

import org.springframework.boot.SpringApplication;

public class TestLoLDataStatsApplication {

	public static void main(String[] args) {
		SpringApplication.from(LoLDataStatsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
