package com.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by varun on 05/01/16.
 */

@Controller
public class AuthenticationController {

    @RequestMapping(value = "/authenticate/logout", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        return "logout";
    }
}
