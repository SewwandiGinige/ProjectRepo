package lk.ech.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by se-9 on 11/1/2017.
 */
@Controller
public class webController {
    @RequestMapping(value = "/makeAppointment")
    public String makeAppointment(){
        return "";
    }


}
