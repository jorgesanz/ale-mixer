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
//		inputParams.setInputFile1(scanner. nextLine());
		inputParams.setInputFile1("C:\\Users\\jsanzbri\\Documents\\git\\ale-mixer\\src\\main\\resources\\A104.ale");

		log.info("Enter file 2 location");
//		inputParams.setInuptFile2(scanner. nextLine());
		inputParams.setInuptFile2("C:\\Users\\jsanzbri\\Documents\\git\\ale-mixer\\src\\main\\resources\\A104-2.ale");

		log.info("Enter output file location");
//		inputParams.setOutputFile(scanner. nextLine());
		inputParams.setOutputFile("C:\\Users\\jsanzbri\\Documents\\git\\ale-mixer\\src\\main\\resources\\A104-out.ale");

		ConfigurableApplicationContext context = SpringApplication.run(AlemixerApplication.class, args);
		AleMixerService aleMixerService =  context.getBean(AleMixerService.class);
		aleMixerService.mix(inputParams);

	}

}
