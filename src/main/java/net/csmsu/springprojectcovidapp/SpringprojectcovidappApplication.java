package net.csmsu.springprojectcovidapp;

import java.awt.EventQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringprojectcovidappApplication implements CommandLineRunner {
	
	@Autowired
	MainFrame frame;

	public static void main(String[] args) {
//		SpringApplication.run(SpringprojectcovidappApplication.class, args);
		SpringApplicationBuilder builder = 
				new SpringApplicationBuilder(SpringprojectcovidappApplication.class);
		builder.lazyInitialization(true).headless(false).run(args);
	}
	@Override
	public void run(String...args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
