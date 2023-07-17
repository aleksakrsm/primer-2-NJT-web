package it.engineering.spring.mvc.ds.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.dto.EntityListDto;
import it.engineering.spring.mvc.ds.dto.ResponseDto;
import it.engineering.spring.mvc.ds.service.CityService;

@RestController
@RequestMapping("/rest/city")
public class CityRestController {
	private CityService cityService;

	@Autowired
	public CityRestController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping(produces = "application/json")
	//public  @ResponseBody ResponseEntity<List<CityDto>> getCities(
	
	public  ResponseEntity<EntityListDto<CityDto>> getCities(
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize) throws Exception {
		
		System.out.println("Retrieving subjects page " + pageNumber + " with size " + pageSize);
		List<CityDto> cities = cityService.findAll(pageNumber, pageSize);
		System.out.println("Number of subjects retrieved: " + cities.size());
		//return ResponseEntity.status(HttpStatus.OK).body(cities);
		return ResponseEntity.ok(new EntityListDto<CityDto>(this.cityService.count(), cities));
	}

	@DeleteMapping("delete/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
		System.out.println("Deleting subject with id " + id);
		try {
			cityService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body("Not Deleted");
		}
	}
	@PostMapping(value = "/add")
    public ResponseEntity<?> save(@RequestBody CityDto cityDto) throws Exception {
		System.out.println(cityDto);
		cityService.save(cityDto);
        return ResponseEntity.ok("Uspesno je sacuvan grad!");
    }
	
}
