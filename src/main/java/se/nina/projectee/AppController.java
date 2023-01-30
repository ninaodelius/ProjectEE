package se.nina.projectee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import se.nina.projectee.flash.FlashModelRepository;

@Controller
public class AppController {

    private final FlashModelRepository flashModelRepository;

    @Autowired
    public AppController(FlashModelRepository flashModelRepository){
        this.flashModelRepository = flashModelRepository;
    }

    @GetMapping("/home")
    public String displayHome(){
        return "home";
    }

}
