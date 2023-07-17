package it.engineering.spring.mvc.ds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.exception.ExistEntityException;
import it.engineering.spring.mvc.ds.service.CityService;

@Controller
@RequestMapping(path = "/city")
//@SessionAttributes(value = "cityDto")
public class CityController {
	@Autowired
	private LocalValidatorFactoryBean validator;

	@Autowired
	@Qualifier(value = "cityServiceImpl")
	private CityService cityService;

	@RequestMapping(path = { "", "/", "/add" }, method = RequestMethod.GET)
	public String add() {//SessionStatus session
		return "city-add";
	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public ModelAndView save(
			@Valid @ModelAttribute("cityDto") CityDto cityDto,
			final BindingResult bindingResult, 
			Model model, SessionStatus session) throws Exception {

		System.out.println("=========================================");
		System.out.println("/save-binding-validation");
		System.out.println(cityDto);
		System.out.println("-----------------------------------------");
		System.out.println(model.asMap());
		System.out.println("-----------------------------------------");
		
		/*
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CityDto>> constraintViolation = validator.validate(cityDto);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		constraintViolation.stream().forEach(el -> System.out.println(el));
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		SpringValidatorAdapter springValidator = new SpringValidatorAdapter(validator);
		springValidator.validate(cityDto, bindingResult);
		*/
		
		if (bindingResult.hasErrors()) {
			System.out.println("========================= HAS ERRORS =======================");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("city-add");
			return modelAndView;
		} else {
			System.out.println("========================= NO ERRORS =======================");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("city-confirm");
			return modelAndView;
		}
	}
	
	@RequestMapping(path = "/confirm", method = RequestMethod.POST)
	public String save(CityDto cityDto, RedirectAttributes redirectAttributes) throws Exception {
		cityService.save(cityDto);
		redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");
		return "redirect:/city/add";
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String viewAll(HttpServletRequest request) throws Exception {
		request.setAttribute("cities", cityService.getAll());
		return "city-list";
	}

	@RequestMapping(path = { "/details" }, method = RequestMethod.GET)
	public String details(HttpServletRequest request) throws Exception {
		System.out.println("City number = " + request.getParameter("number"));
		CityDto cityDto = cityService.findById(Long.parseLong(request.getParameter("number")));
		if (cityDto == null) {

		} else {
			request.setAttribute("city", cityDto);
		}
		return "city-view";
	}
	
	@ExceptionHandler(ExistEntityException.class)
	public ModelAndView exeptionHandlerEntityExist(ExistEntityException eee) {
		System.out.println("=================================================================");
		System.out.println(eee.getMessage());
		System.out.println("============= Objekat nad kojim se desila greska je:==============");
		System.out.println(eee.getEntity());
		System.out.println("=================================================================");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("city-add-binding");
		modelAndView.addObject("cityDto",(CityDto)eee.getEntity() );
		modelAndView.addObject("exception", eee.getMessage());
		//modelAndView.setViewName("redirect:/city/add-binding");
		return modelAndView;
	}
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		if (binder.getTarget() instanceof CityDto) {
			binder.setValidator(validator);
		}
	}
	
	@ModelAttribute(name = "cityDto")
	private CityDto getCityDto() {
		return new CityDto(0L, "n/a");
	}
	
}
