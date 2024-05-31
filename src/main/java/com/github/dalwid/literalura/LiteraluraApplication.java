package com.github.dalwid.literalura;

import com.github.dalwid.literalura.main.RunMain;
import com.github.dalwid.literalura.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AuthorService service;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RunMain manu = new RunMain(service);
	}
}
