package es.jorgesanz.alemixer;

import es.jorgesanz.alemixer.model.InputParams;
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
public class AlemixerApplication{

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {

		Scanner scanner = new Scanner( System. in);

		InputParams inputParams = new InputParams();

		log.info("Enter file 1 location");
		inputParams.setInputFile1(scanner. nextLine());

		log.info("Enter file 2 location");
		inputParams.setInuptFile2(scanner. nextLine());

		log.info("Enter output file location");
		inputParams.setOutputFile(scanner. nextLine());

		ConfigurableApplicationContext context = SpringApplication.run(AlemixerApplication.class, args);
		AleMixerService aleMixerService =  context.getBean(AleMixerService.class);
		aleMixerService.mix(inputParams);

	}

}
