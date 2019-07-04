package com.in28minutes.springboot.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.service.TodoService;

@Controller
public class TodoController {
	
    private static CamdenDb camdenDb=new CamdenDb();

	@Autowired
	private HttpServletRequest request;
	@Autowired
	TodoService service;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) throws ClassNotFoundException, SQLException {
		//String name = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodos(""));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}

/*	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model),
				"Default Desc", new Date(), false));
		return "todo";
	}*/

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {

		if(id==1)
			throw new RuntimeException("Something went wrong");
		
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}

	/*@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}*/

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(/*@RequestParam int eventId*/) throws ClassNotFoundException, SQLException {
		
		System.out.println("  submitt  ...action.."+request.getParameter("submit"));
    String[] eventId=request.getParameterValues("eventId");
    Integer eventIds[] = {}; 
    if(request.getParameter("submit").equals("Reject"))
    {
	service.deleteEvents(eventId);
    }else {
    	service.updateTodo(eventId);
    }
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	@ResponseBody
	public List<User> addTodo(ModelMap model, @Valid Todo todo, BindingResult result,@RequestParam int req1) throws ClassNotFoundException, SQLException {

		if (result.hasErrors()) {
			return null;
		}
		List<User> user=service.retrieveTodos("");

		/*service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),
				false);*/
		return user;
	}
	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
	@ResponseBody
	public Integer moveEmployee() throws ClassNotFoundException, SQLException {

		Integer user=camdenDb.getEmployeeDetails();

		/*service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),
				false);*/
		return user;
	}
}
