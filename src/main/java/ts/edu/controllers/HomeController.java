package ts.edu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ts.edu.models.User;

import java.util.Date;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        Date date = new Date();
        model.addAttribute("date", date.getTime());
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login", "user", new User());
    }

    public ModelAndView checkUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject(user);

        return modelAndView;
    }

}
