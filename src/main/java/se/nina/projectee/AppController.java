package se.nina.projectee;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.nina.projectee.flash.FlashModel;
import se.nina.projectee.flash.FlashModelDetailsService;

@Controller
@RequestMapping
public class AppController {



    private final FlashModelDetailsService flashModelDetailsService;

    public AppController(FlashModelDetailsService flashModelDetailsService) {
        this.flashModelDetailsService = flashModelDetailsService;
    }

    @GetMapping("/home")
    public String displayHome(){
        return "home";
    }


    @GetMapping("/signup")
    public String showSaveNewFlash(FlashModel flashModel){

        return "signup";
    }

    @PostMapping("/signup")
    public String saveNewFlash(@Valid FlashModel flashModel, BindingResult result, Model model){

        if(result.hasErrors()){
            return "signup";
        }

        flashModel.setAccountNonExpired(true);
        flashModel.setAccountNonLocked(true);
        flashModel.setCredentialsNonExpired(true);
        flashModel.setEnabled(true);

        flashModelDetailsService.save(flashModel);

        model.addAttribute("flashes", flashModelDetailsService.findAll());

        //redirect to startpage to prevent duplicate submissions
        return "redirect:/";
    }

    /*@PostMapping("/login")
    public String login(){
        return "login";
    }*/

}
