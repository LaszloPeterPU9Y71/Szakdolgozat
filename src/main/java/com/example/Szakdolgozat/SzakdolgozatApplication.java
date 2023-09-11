package com.example.Szakdolgozat;

import com.example.Szakdolgozat.domain.Ceg;
import com.example.Szakdolgozat.repository.CegRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


@SpringBootApplication
@Slf4j
public class SzakdolgozatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SzakdolgozatApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CegRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(Ceg.builder().id(1).company("Ceg1").postalCode(9023).town("Győr").street("Attila utca 49.").taxNumber("1234567-2-09").status(true).build());
			repository.save(new Ceg(2, "Ceg2", 9023, "Győr","Attila utca 10", "1234567-2-08", true));
			repository.save(new Ceg(3, "Ceg3", 9024,"Győr", "József A. u. 12", "adoszam3", true));




			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Ceg customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<Ceg> cegOptional = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(cegOptional.map(Ceg::toString).orElse(""));
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByCompany("Ceg2").forEach(ceg -> log.info(ceg.toString()));
			log.info("");
		};
	}


}
