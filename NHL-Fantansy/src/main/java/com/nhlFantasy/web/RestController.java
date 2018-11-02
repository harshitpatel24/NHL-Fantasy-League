package com.nhlFantasy.web;
import org.springframework.web.bind.annotation.RequestMapping;


@org.springframework.web.bind.annotation.RestController
public class RestController {

	@RequestMapping(value = "/api/hello")
	 public String greet() {
	  return "Hello from the other side124342343!!!";
	 }
	
	
}
