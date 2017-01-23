package controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

/**
 * Created by Maggouh on 20/01/17.
 */
@Controller
public class TestController {

    @PreAuthorize("ROLE_TRAINEE")
    public String test() {
        return "Trainee";
    }

}
