package com.nhlFantasy;

import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends org.springframework.boot.web.support.SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NhlFantansyApplication.class);
	}

}
