package co.grandcircus.REST.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.grandcircus.REST.Api.dao.RestApiDao;
import co.grandcircus.REST.Api.entity.Movie;


@SpringBootApplication
public class RestApiApplication {

	
	public static void main(String[] args) {
		
		SpringApplication.run(RestApiApplication.class, args);
	}

}
