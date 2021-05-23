package it.prova.gestionepokerspringrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionepokerspringrest.service.RuoloService;
import it.prova.gestionepokerspringrest.service.UtenteService;

@SpringBootApplication
public class GestionepokerspringrestApplication implements CommandLineRunner{
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(GestionepokerspringrestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
