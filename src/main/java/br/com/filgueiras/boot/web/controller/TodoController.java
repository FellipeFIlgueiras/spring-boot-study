package br.com.filgueiras.boot.web.controller;

import br.com.filgueiras.boot.web.model.Todo;
import br.com.filgueiras.boot.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap modelMap) {
        String username = getLoggedInUserName();
        modelMap.put("todos", todoService.retrieveTodos(username));
        return "list-todos";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap modelMap) {
        Todo todo = todoService.retrieveTodo(id);
        modelMap.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()){
            return "todo";
        }
        String username = getLoggedInUserName();
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String addTodoPage(ModelMap modelMap) {
        modelMap.addAttribute("todo", new Todo(0, getLoggedInUserName(), "", new Date(), false));
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()){
            return "todo";
        }
        String username = getLoggedInUserName();
        todoService.addTodo(username, todo.getDescription(), new Date(), false);
        return "redirect:/list-todos";
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

}
