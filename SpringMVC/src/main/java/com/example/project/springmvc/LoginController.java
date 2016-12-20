package com.example.project.springmvc;

/**
 * Spring MVC Login Controller.
 *
 * Created by mlglenn on 12/20/2016.
 */
@Controller
public class LoginController {

    @RequestMapping(value="/login")
    @ResponseBody
    public String sayHello() {
        return "Hello World!";
    }
}
