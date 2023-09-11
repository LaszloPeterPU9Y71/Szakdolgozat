package com.example.Szakdolgozat;

import com.example.Szakdolgozat.domain.*;
import com.example.Szakdolgozat.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
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

	@Bean
	public CommandLineRunner OwnerCompanyDemo(OwnerCompanyRepository repository) {
		return (args) -> {
			// save a few users
			repository.save(OwnerCompanyEntity.builder().id(1).name("Ceg1").postalCode(9023).town("Győr").street("Attila utca 49.").taxNumber("1234567-2-09").accountNumber("12313213-321321321-3213213").status(true).build());
			repository.save(new OwnerCompanyEntity(2, "Ceg22", 9023, "Győr", "Attila utca 10", "321654-654654-654654", "1234567-2-08", true));
			repository.save(new OwnerCompanyEntity(3, "Ceg3", 9024, "Győr", "József A. u. 12", "321321-321321-321312", "adoszam3", true));


			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (OwnerCompanyEntity customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<OwnerCompanyEntity> cegOptional = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(cegOptional.map(OwnerCompanyEntity::toString).orElse(""));
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Ceg22'):");
			log.info("--------------------------------------------");
			repository.findByName("Ceg22").forEach(OwnerCompanyEntity -> log.info(OwnerCompanyEntity.toString()));
			log.info("");
		};
	}
	@Bean
	public CommandLineRunner OwnerCompanyEmployeeDemo(OwnerCompanyEmployeeRepository repository) {
		return (args) -> {
			// save a few users
			repository.save(OwnerCompanyEmloyeeEntity.builder().id(1).name("Ceg1").telNum(706219023).email("Győr").password("Attila utca 49.").title("Alkalmazott").status(true).build());
			repository.save(new OwnerCompanyEmloyeeEntity(2, "Ceg33", 9023, "Győr", "Attila utca 10", "1234567-2-08", true));
			repository.save(new OwnerCompanyEmloyeeEntity(3, "Ceg3", 9024, "Győr", "József A. u. 12", "adoszam3", true));


			// fetch all users
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (OwnerCompanyEmloyeeEntity User : repository.findAll()) {
				log.info(User.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<OwnerCompanyEmloyeeEntity> UserOptional = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(UserOptional.map(OwnerCompanyEmloyeeEntity::toString).orElse(""));
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Ceg33'):");
			log.info("--------------------------------------------");
			repository.findByName("Ceg33").forEach(UserEntity -> log.info(UserEntity.toString()));
			log.info("");
		};
	}
	@Bean
	public CommandLineRunner ToolDemo(ToolRepository repository) {
		return (args) -> {
			// save a few users
			repository.save(ToolEntity.builder().id(1).name("Ceg66").typeNumber("123").itemNumber("3601AST321").serialNumber("123456789").dateOfReceiving(new Date()).build());
			repository.save(new ToolEntity(2, "Ceg", "9023", "654654", "Attila utca 10",new Date()));
			repository.save(new ToolEntity(3, "Ceg3", "9024", "Győr", "József A. u. 12", new Date()));


			// fetch all users
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (ToolEntity Tool : repository.findAll()) {
				log.info(Tool.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<ToolEntity> UserOptional = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(UserOptional.map(ToolEntity::toString).orElse(""));
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Ceg66'):");
			log.info("--------------------------------------------");
			repository.findByName("Ceg66").forEach(ToolEntity -> log.info(ToolEntity.toString()));
			log.info("");
		};
	}

}


