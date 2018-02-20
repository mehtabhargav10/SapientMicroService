package com.sapient.microservices.product;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sapient.microservices.product")
public class ProductConfiguration {

	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}

}
