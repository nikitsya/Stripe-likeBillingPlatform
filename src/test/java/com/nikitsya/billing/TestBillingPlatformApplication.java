package com.nikitsya.billing;

import org.springframework.boot.SpringApplication;

public class TestBillingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.from(BillingPlatformApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
