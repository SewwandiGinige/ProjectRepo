package lk.ech.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by se-9 on 10/16/2017.
 */
@Controller
public class mainController {
        @RequestMapping("/")
        public String getIndex() {
            return "index";
        }

}
