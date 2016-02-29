package ts.edu.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ts.edu.models.User;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

@Controller
//@SessionAttributes("user")
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private MessageSource messageSource;

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

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(@Valid @ModelAttribute("user") User user,
                                  BindingResult bindingResult,
                                  SessionStatus sessionStatus,
                                  Locale locale) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject(user);
        modelAndView.addObject("local", locale.getDisplayName());

        String[] localeStr = new String[] { locale.getDisplayName(locale) };
        logger.info(messageSource.getMessage("locale", localeStr, locale) + " [LOCALE]");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errors", "Invalid data.");
        }


        return modelAndView;
    }

    @RequestMapping(value = "/get-json-user", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody User getJsonUser(@RequestParam("name") String name) {
        User user = new User();
        user.setName(name);

        return user;
    }

//    @ModelAttribute
//    public User createUser()
//    {
//        return new User();
//    }

//    @RequestMapping(value = "/get-json-user/{name}", method = RequestMethod.GET, produces = "application/json")
//    public @ResponseBody User getJsonUser(@PathVariable("name") String name) {
//        User user = new User();
//        user.setName(name);
//
//        return user;
//    }

    @RequestMapping(value = "/set-json-user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> setJsonUser(@RequestBody User user) {
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

}