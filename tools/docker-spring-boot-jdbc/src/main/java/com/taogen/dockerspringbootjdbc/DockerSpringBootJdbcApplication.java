package com.taogen.dockerspringbootjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@SpringBootApplication
@RestController
public class DockerSpringBootJdbcApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping
	public String hello() {
		Map<String, Object> resultMap = jdbcTemplate.queryForMap("select version()");
		return resultMap.toString() + new Date();
	}

	public static void main(String[] args) {
		SpringApplication.run(DockerSpringBootJdbcApplication.class, args);
	}

}
