package com.java.choretracker.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.choretracker.models.Chore;
import com.java.choretracker.models.LoginUser;
import com.java.choretracker.models.User;
import com.java.choretracker.services.ChoreService;
import com.java.choretracker.services.UserService;


    
@Controller
public class HomeController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ChoreService choreService;
    
    
    @GetMapping("/")
    public String index( @ModelAttribute("newUser") User newUser, @ModelAttribute("newLogin") LoginUser newLogin) {
    	return "index.jsp";
    }
    
    

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {

    	User registeredUser = userService.register(newUser, result);

        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        session.setAttribute("userId", registeredUser.getId());
        return "redirect:/home";
    }
    
    
    
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
    	
        User user = userService.login(newLogin, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }

        session.setAttribute("userId", user.getId());
        return "redirect:/home";
    }
    
    
    
    
    @GetMapping("/home")
    public String dashboard( HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {return "redirect:/";}

        User user = userService.findUser(userId);
        List<Chore> chores= choreService.allChores();
        
        model.addAttribute("user", user);
        model.addAttribute("chores", chores);
        return "home.jsp";
    }
    
   
    @PostMapping("/chores/{id}/add")
    public String addChore(@PathVariable("id") Long id, HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {return "redirect:/";}
        
        Chore chore = choreService.findChore(id);
        User user = userService.findUser(userId);
        chore.setEmployee(user);
        choreService.updateChore(chore);
        
        return "redirect:/home";
    }

    
    @GetMapping("/addChore")
    public String addTeam( @ModelAttribute("newChore") Chore newChore, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {return "redirect:/";}

        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "newChore.jsp";
    }
   
    
    
    @PostMapping("/addChore")
    public String saveChore( @Valid @ModelAttribute("newChore") Chore newChore, BindingResult result) {
    	if (result.hasErrors()) {return "newChore.jsp";} 
    	choreService.createChore(newChore);
		return "redirect:/home";
    }
    
    
    
    
    @GetMapping("/view/{id}")
    public String showChore( @PathVariable("id") Long choreId, Model model,  HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {return "redirect:/";}

        User user = userService.findUser(userId);
        Chore chore = choreService.findChore(choreId);
        
        model.addAttribute("user", user);
        model.addAttribute("chore" , chore);
        return "viewChore.jsp";
    }
    
    
    
    @GetMapping("/edit/{id}")
    public String editChore( @PathVariable("id") Long id, Model model, HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {return "redirect:/";}

    	
        User user = userService.findUser(userId); 
        Chore chore = choreService.findChore(id);
        
        if (!user.equals(chore.getEmployer())) {return "redirect:/home";}

        model.addAttribute("chore", chore);
        model.addAttribute("user", user);
        
        return "edit.jsp";
    }
    
    
    @RequestMapping(value="/edit/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("chore") Chore chore, BindingResult result) {
    	if (result.hasErrors()) {return "edit.jsp";} 
    	choreService.updateChore(chore);
    	
        return "redirect:/home";
        
    }
    
    
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    
    
    
    @RequestMapping(value="/chores/delete/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
    	choreService.deleteChore(id);
        return "redirect:/home";
    }
    
    
    

}
    

