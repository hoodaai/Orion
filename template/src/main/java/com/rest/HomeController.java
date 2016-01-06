package com.rest;

import com.model.FaceBookPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varun on 31/12/15.
 */
@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {

        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
        return new ModelAndView("welcome", "message", message);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        return "index";
    }

}
