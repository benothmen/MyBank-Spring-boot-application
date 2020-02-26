package com.sid;

import com.sid.Service.IBankMetier;
import com.sid.dao.ClientRepository;
import com.sid.dao.CompteRepository;
import com.sid.dao.OperationRepository;
import com.sid.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class MyBankApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBankMetier iBankMetier;
	public static void main(String[] args) {
		SpringApplication.run(MyBankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Client c1=clientRepository.save(new Client("zied","zied@gmail.com"));
		Client c2=clientRepository.save(new Client("bouthaina","bouthaian@gmail.com"));

		Compte cp1=compteRepository.save(new CompteCourant("c1",new Date(),(double)6000,c1,9000));
		Compte cp2=compteRepository.save(new CompteCourant("c2",new Date(),(double)3000,c2,6000));

		operationRepository.save(new Versement(new Date(),9000,cp1));
		operationRepository.save(new Versement(new Date(),6000,cp1));
		operationRepository.save(new Versement(new Date(),2000,cp1));
		operationRepository.save(new Retrait(new Date(),9000,cp1));

		operationRepository.save(new Versement(new Date(),5000,cp2));
		operationRepository.save(new Versement(new Date(),6000,cp2));
		operationRepository.save(new Versement(new Date(),2000,cp2));
		operationRepository.save(new Retrait(new Date(),9000,cp2));

		iBankMetier.verser("c1",11111);*/
	}
}
