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
import se.nina.projectee.flash.FlashModelRepository;

@Controller
@RequestMapping
public class AppController {



    private final FlashModelDetailsService flashModelDetailsService;
    private final FlashModelRepository flashModelRepository;

    public AppController(FlashModelDetailsService flashModelDetailsService, FlashModelRepository flashModelRepository) {
        this.flashModelDetailsService = flashModelDetailsService;
        this.flashModelRepository = flashModelRepository;
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


        /*flashModel.setAccountNonExpired(true);
        flashModel.setAccountNonLocked(true);
        flashModel.setCredentialsNonExpired(true);
        flashModel.setEnabled(true);*/

        flashModelDetailsService.save(flashModel);

        model.addAttribute("flashes", flashModelDetailsService.findAll());

        //redirect to startpage to prevent duplicate submissions
        return "redirect:/";
    }

}
