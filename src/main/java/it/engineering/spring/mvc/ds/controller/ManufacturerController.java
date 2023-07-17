package it.engineering.spring.mvc.ds.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.dto.ContactPersonDto;
import it.engineering.spring.mvc.ds.dto.ManufacturerDto;
import it.engineering.spring.mvc.ds.service.CityService;
import it.engineering.spring.mvc.ds.service.ManufacturerService;
import it.engineering.spring.mvc.ds.validator.ManufacturerValidator;

@Controller
@RequestMapping(path = {"/manufacturer","/m"})
public class ManufacturerController {
	private final ManufacturerService manufacturerService;
	private final CityService cityService;
	
	@Autowired
	public ManufacturerController(ManufacturerService manufacturerService,CityService cityService) {
		this.manufacturerService = manufacturerService;
		this.cityService = cityService;
	}
	
	@GetMapping(path = "/add")
	public ModelAndView add() throws Exception {
		ModelAndView modelAndView = new ModelAndView("manufacturer/manufacturer-add");
		return modelAndView;
	}

	@GetMapping(path = "/list")
	public ModelAndView list() throws Exception {
		List<ManufacturerDto> manufactures = manufacturerService.getAll();
		ModelAndView modelAndView = new ModelAndView("manufacturer/manufacturer-list");
		modelAndView.addObject("manufactures", manufactures);		
		return modelAndView;
	}
	
	@GetMapping(path = "/details/id/{id}")
	public ModelAndView details(@PathVariable (name = "id") Long id) throws Exception {
		System.out.println("================================ DETAILS ==============================");
		System.out.println("ID: " + id);
		
		ManufacturerDto manufacturerDto = manufacturerService.findById(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacturer/manufacturer-details");
		modelAndView.addObject("manufacturerDto", manufacturerDto);
		
		return modelAndView;
	}
	
	@GetMapping(path = "/edit/id/{id}")
	public ModelAndView edit(@PathVariable (name = "id") Long id) throws Exception {
		System.out.println("================================ EDIT ==============================");
		System.out.println("ID: " + id);
		
		ManufacturerDto manufacturerDto = manufacturerService.findById(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacturer/manufacturer-edit");
		modelAndView.addObject("manufacturerDto", manufacturerDto);
		return modelAndView;
	}
	
	@PostMapping(path = "/confirm")
	public ModelAndView confirm(@Valid @ModelAttribute(name = "manufacturerDto")  ManufacturerDto manufacturerDto, 
								Errors errors) throws Exception {
		System.out.println("================================ CONFIRM ==============================");
		System.out.println("manufacturerDto:" + manufacturerDto);
		System.out.println("=======================================================================");
		
		String view = "manufacturer/manufacturer-add";
		ModelAndView modelAndView = new ModelAndView();
		if(errors.hasErrors()) {
			System.out.println("Imamo greske kod validacije...");
			if (manufacturerDto.getId()!=null) view = "manufacturer/manufacturer-edit";
		}else view = "manufacturer/manufacturer-confirm";
		
		modelAndView.setViewName(view);
		return modelAndView;
	}
	
	@PostMapping(path = "/saveOrUpdate")
	public ModelAndView saveOrUpdate(
			@Valid @ModelAttribute(name = "manufacturerDto")  ManufacturerDto manufacturerDto, 
			Errors errors) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String view = "manufacturer/manufacturer-add";
		if(errors.hasErrors()) {
			System.out.println("Imamo greske kod validacije...");
			if (manufacturerDto.getId()!=null) view = "manufacturer/manufacturer-edit";
		}else {
			//save or update
			if (manufacturerDto.getId()==null) {
				List<ContactPersonDto> persons = new ArrayList<ContactPersonDto>();
				persons.add(new ContactPersonDto(null, "X", "X"));
				persons.add(new ContactPersonDto(null, "Y", "Y"));
				persons.add(new ContactPersonDto(null, "Z", "Z"));
				manufacturerDto.setContactPersonsDto(persons);
								
				System.out.println("ZOVI SERVICE -> SAVE");
				manufacturerService.save(manufacturerDto);
				view ="redirect:/manufacturer/add";
			}
			else {
				//
				List<ContactPersonDto> persons = new ArrayList<ContactPersonDto>();
				persons.add(new ContactPersonDto(46L, "X", "X"));
				//persons.add(new ContactPersonDto(47L, "Y", "Y"));
				//persons.add(new ContactPersonDto(48L, "Z", "Z"));
				persons.add(new ContactPersonDto(null, "D", "D"));
				manufacturerDto.setContactPersonsDto(persons);
				
				
				
				//radi update
				manufacturerDto = manufacturerService.update(manufacturerDto);
				view = "redirect:/manufacturer/details/id/" + manufacturerDto.getId();
			}
		}
		
		modelAndView.setViewName(view);
		return modelAndView;
	}
	
	
	@ModelAttribute(name = "manufacturerDto")
	private ManufacturerDto manufacturerDto() {
		System.out.println("*****************************************************");
		System.out.println("kreiran je: @ModelAttribute(name = \"manufacturerDto\")");
		
		ManufacturerDto manufacturerDto = new ManufacturerDto();
		manufacturerDto.setName("n/a");
		return manufacturerDto;
	}
	
	@ModelAttribute(name = "cities")
	private List<CityDto> getCities() throws Exception{
		return cityService.getAll();
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("====================== @InitBinder ====================");
		System.out.println("public void initBinder(WebDataBinder binder)");
		binder.addValidators(new ManufacturerValidator());
		
	}
	
	//ToDo greske koje vraca SERVICE metoda
}
