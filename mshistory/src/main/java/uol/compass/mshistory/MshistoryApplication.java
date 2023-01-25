package uol.compass.mshistory;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MshistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MshistoryApplication.class, args);
	}

}
