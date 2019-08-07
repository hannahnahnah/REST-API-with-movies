package co.grandcircus.REST.Api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.REST.Api.dao.RestApiDao;
import co.grandcircus.REST.Api.entity.Movie;


@RestController
public class RestApiController {
	
	@Autowired
	private RestApiDao dao;
	
	@GetMapping("/")
	public ModelAndView redirect() {
		return new ModelAndView("redirect:/movies");
	}
	
	@GetMapping("/movies")
	public List<Movie> listMovies(
			@RequestParam(value="category", required=false) String category,
			@RequestParam(value="title", required=false) String title
			) {
		
		if ((category == null || category.isEmpty()) && ((title == null || title.isEmpty()))) {
			return dao.findAll();
		} else if (title == null || title.isEmpty()) {
			return dao.findByCategory(category);
		} else {
			return dao.findByTitleContainsIgnoreCase(title);
		}
	
	}
	
	@GetMapping("/movies/random")
	public Movie randomMovie(
			@RequestParam(value="category", required=false) String category,
			@RequestParam(value="title", required=false) String title
			) {
		if (category == null || category.isEmpty()) {
			Long id = (long)((Math.random() * 100) + 1);
			return dao.findById(id).get();
		} else {
			List<Movie> moviesInCategory = dao.findByCategory(category);
			int length = moviesInCategory.size();
			int id = (int)((Math.random() * length) + 1);
			return moviesInCategory.get(id);
		}
	}
	
	@GetMapping("movies/random-list")
    public List<Movie> randomMovieList(@RequestParam("quantity")Integer quantity) {
        List<Movie> movieList = dao.findAll();
        List<Movie> randomMovieList = new ArrayList<Movie>();
        Collections.shuffle(movieList);
        for (int i = 0; i < quantity; ++i) {
        	randomMovieList.add(movieList.get(i));
        }
        return randomMovieList;
    }
	
	
	
	@GetMapping("/categories")
	public List<String> getAllCategories() {
		List<Movie> movieList = dao.findAll();
		List<String> movieCategories = new ArrayList<String>();
		for (Movie movie: movieList) {
			if (!movieCategories.contains(movie.getCategory())) {
				movieCategories.add(movie.getCategory());
			}
		} return movieCategories;
	}
	
	@GetMapping("/movies/{id}") 
	public Movie getProduct(
			@PathVariable("id") Long id
			) {
		return dao.findById(id).get();
	}

}
