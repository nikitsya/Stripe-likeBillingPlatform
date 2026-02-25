package com.nikitsya.billing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.DockerClientFactory;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@EnabledIf("isDockerAvailable")
class BillingPlatformApplicationTests {

	static boolean isDockerAvailable() {
		try {
			return DockerClientFactory.instance().isDockerAvailable();
		}
		catch (Exception ex) {
			return false;
		}
	}

	@Test
	void contextLoads() {
	}

}
