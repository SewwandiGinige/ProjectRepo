package lk.ech.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by se-9 on 11/2/2017.
 */
@Controller
public class mainController {
    @RequestMapping(value = "")
    public String getAppointmentDetails(){
        return "string";
    }
}
