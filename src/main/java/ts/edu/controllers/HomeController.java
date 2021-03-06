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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import ts.edu.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("user")
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
                                  RedirectAttributes redirectAttributes,
                                  Locale locale) {

        ModelAndView modelAndView = new ModelAndView();

        String[] localeStr = new String[] { locale.getDisplayName(locale) };
        logger.info(messageSource.getMessage("locale", localeStr, locale) + " [LOCALE]");

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login");
            modelAndView.addObject("errors", "Invalid data.");
            modelAndView.addObject(user);
            modelAndView.addObject("local", locale.getDisplayName());
        } else {
//            redirectAttributes.addFlashAttribute("locale", locale.getLanguage());
//            modelAndView.setViewName("redirect:/profile");
            RedirectView redirectView = new RedirectView();
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            redirectView.setUrl("profile");
            modelAndView.setView(redirectView);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(@ModelAttribute User user, HttpServletRequest request) {

        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);

        if (map != null) {
            logger.info("There was a redirect [REDIRECT_TO_PROFILE]");
        } else {
            logger.info("User was refreshed [REFRESHED_PROFILE]");
        }

        return "profile";
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