package co.grandcircus.REST.Api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.REST.Api.entity.Movie;

public interface RestApiDao extends JpaRepository<Movie, Long> {
	
	List<Movie> findByTitleContainsIgnoreCase(String title);
	
	List<Movie> findByCategory(String category);
	
	
}//class
