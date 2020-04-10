package es.jorgesanz.alemixer;

import es.jorgesanz.alemixer.service.AleMixerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AlemixerApplication {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(AlemixerApplication.class, args);
		AleMixerService aleMixerService =  context.getBean(AleMixerService.class);
		aleMixerService.mix();

	}

}
