package com.example.Szakdolgozat;

import com.example.Szakdolgozat.domain.CompanyEntity;
import com.example.Szakdolgozat.domain.SparePartsEntity;
import com.example.Szakdolgozat.domain.UserEntity;
import com.example.Szakdolgozat.repository.CompanyRepository;
import com.example.Szakdolgozat.repository.SparePartsRepository;
import com.example.Szakdolgozat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


@SpringBootApplication
@Slf4j
public class ThesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThesisApplication.class, args);
	}

	@Bean
	public CommandLineRunner CompanyDemo(CompanyRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(CompanyEntity.builder().id(1).name("Ceg1").postalCode(9023).town("Győr").street("Attila utca 49.").taxNumber("1234567-2-09").status(true).build());
			repository.save(new CompanyEntity(2, "Ceg2", 9023, "Győr", "Attila utca 10", "1234567-2-08", true));
			repository.save(new CompanyEntity(3, "Ceg3", 9024, "Győr", "József A. u. 12", "adoszam3", true));


			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (CompanyEntity customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<CompanyEntity> cegOptional = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(cegOptional.map(CompanyEntity::toString).orElse(""));
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Ceg2'):");
			log.info("--------------------------------------------");
			repository.findByName("Ceg2").forEach(companyEntity -> log.info(companyEntity.toString()));
			log.info("");
		};
	}

	@Bean
	public CommandLineRunner UserDemo(UserRepository repository) {
		return (args) -> {
			// save a few users
			repository.save(UserEntity.builder().id(1).name("Ceg1").telNum(706219023).email("Győr").password("Attila utca 49.").title("Alkalmazott").status(true).build());
			repository.save(new UserEntity(2, "Ceg2", 9023, "Győr", "Attila utca 10", "1234567-2-08", true));
			repository.save(new UserEntity(3, "Ceg3", 9024, "Győr", "József A. u. 12", "adoszam3", true));


			// fetch all users
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (UserEntity User : repository.findAll()) {
				log.info(User.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<UserEntity> UserOptional = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(UserOptional.map(UserEntity::toString).orElse(""));
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Ceg2'):");
			log.info("--------------------------------------------");
			repository.findByName("Ceg2").forEach(UserEntity -> log.info(UserEntity.toString()));
			log.info("");
		};
	}

	@Bean
	public CommandLineRunner SparePartsDemo(SparePartsRepository repository) {
		return (args) -> {
			// save a few users
			repository.save(SparePartsEntity.builder().id(1).itemNumber("Ceg1").nettoBuyingValue(9023).nettoSellingValue(5555).pieceOnStock(5).build());
			repository.save(new SparePartsEntity(2, "999", 9023, 9999, 5555));
			repository.save(new SparePartsEntity(3, "3", 999, 999, 999));


			// fetch all users
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (SparePartsEntity User : repository.findAll()) {
				log.info(User.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<SparePartsEntity> SparePartsOptional = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(SparePartsOptional.map(SparePartsEntity::toString).orElse(""));
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByitemNumber('3'):");
			log.info("--------------------------------------------");
			repository.findByitemNumber("3").forEach(UserEntity -> log.info(UserEntity.toString()));
			log.info("");
		};
	}

}
