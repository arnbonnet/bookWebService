package fr.iocean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("fr.iocean")
public class App {
	public App() {
		System.out.println("Application started");
	}
	
	public static void main(String[] args) {
	}

}
