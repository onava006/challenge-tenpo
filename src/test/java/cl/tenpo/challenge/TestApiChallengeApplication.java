package cl.tenpo.challenge;

import org.springframework.boot.SpringApplication;

public class TestApiChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiChallengeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
