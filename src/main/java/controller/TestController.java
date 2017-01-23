package controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

/**
 * Created by Maggouh on 20/01/17.
 */
@Controller

public class TestController {
    @Secured("ROLE_TRAINEE")
    public String test() {
        return "Trainee";
    }

}
