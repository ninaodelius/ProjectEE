package se.nina.projectee;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.nina.projectee.flash.AppPasswordConfig;
import se.nina.projectee.flash.FlashModel;
import se.nina.projectee.flash.FlashModelDetailsService;
import se.nina.projectee.flash.auth.FlashRoles;

import java.util.List;

@Controller
//@CrossOrigin(value = "localhost:3000")
public class AppController {


    private final FlashModelDetailsService flashModelDetailsService;
    private final AppPasswordConfig appPasswordConfig;


    public AppController(FlashModelDetailsService flashModelDetailsService, AppPasswordConfig appPasswordConfig) {
        this.flashModelDetailsService = flashModelDetailsService;
        this.appPasswordConfig = appPasswordConfig;
    }

    @GetMapping("/home")
    public String displayHome(){
        return "home";
    }


    @GetMapping("/find/{username}")
    @ResponseBody
    public FlashModel findByUsername(@PathVariable String username) {

        System.out.println(flashModelDetailsService.loadUserByUsername(username));

        return flashModelDetailsService.loadUserByUsername(username);
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
        flashModel.setPassword(appPasswordConfig.bCryptPassword().encode(flashModel.getPassword()));
        flashModel.setAccountNonExpired(true);
        flashModel.setAccountNonLocked(true);
        flashModel.setCredentialsNonExpired(true);
        flashModel.setEnabled(true);


        String role = String.valueOf(flashModel.getAuthorities().iterator().next());

        switch (role) {
            case "ADMIN" ->  flashModel.setAuthorities(FlashRoles.ADMIN.getGrantedAuthorities());
            case "FLASH" -> flashModel.setAuthorities(FlashRoles.FLASH.getGrantedAuthorities());
        }

        //flashModel.setAuthorities(FlashRoles.ADMIN.getGrantedAuthorities());

        flashModelDetailsService.save(flashModel);

        //model.addAttribute("flashes", flashModelDetailsService.findAll()); //testar ta bort denna

        //redirect to startpage to prevent duplicate submissions
        return "redirect:/";
    }

    @GetMapping("/admin")
    //@PreAuthorize("hasrole('ROLE_ADMIN')")
    public String displayAdmin(Model theModel){

        List<FlashModel> theFlashes = flashModelDetailsService.findAll();

        theModel.addAttribute("flashes", theFlashes);


        return "adminPage";
    }

    @GetMapping("/flash")
    //@PreAuthorize("hasrole('ROLE_FLASH')")
    public String displayFlash(){
        return "flashPage";
    }


}
