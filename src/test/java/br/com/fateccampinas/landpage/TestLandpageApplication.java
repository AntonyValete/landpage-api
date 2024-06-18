package br.com.fateccampinas.landpage;

import org.springframework.boot.SpringApplication;

public class TestLandpageApplication {

	public static void main(String[] args) {
		SpringApplication.from(LandpageApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
