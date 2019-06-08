package com.example;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.service.StorageService;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class UppApplication implements CommandLineRunner{

	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(UppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

}

