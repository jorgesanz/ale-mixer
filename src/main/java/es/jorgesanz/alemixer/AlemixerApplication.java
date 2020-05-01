package es.jorgesanz.alemixer;

import es.jorgesanz.alemixer.service.AleMixerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class AlemixerApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner( System. in);

		args = new String[3];

		log.info("Enter file 1 location");
		args[0]= scanner. nextLine();

		log.info("Enter file 2 location");
		args[1]= scanner. nextLine();

		log.info("Enter output file location");
		args[2]= scanner. nextLine();
		ConfigurableApplicationContext context = SpringApplication.run(AlemixerApplication.class, args);
		AleMixerService aleMixerService =  context.getBean(AleMixerService.class);
		aleMixerService.mix();

	}

	@Override
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");

		for (int i = 0; i < args.length; ++i) {
			log.info("args[{}]: {}", i, args[i]);
		}
	}
}
