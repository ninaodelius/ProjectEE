package se.nina.projectee;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.nina.projectee.flash.AppPasswordConfig;
import se.nina.projectee.flash.FlashModel;
import se.nina.projectee.flash.FlashModelDetailsService;
import se.nina.projectee.flash.auth.FlashRoles;

@Controller
//@CrossOrigin(value = "localhost:3000")
public class AppController {


    private final FlashModelDetailsService flashModelDetailsService;
    private final AppPasswordConfig appPasswordConfig;


    public AppController(FlashModelDetailsService flashModelDetailsService, AppPasswordConfig appPasswordConfig) {
        this.flashModelDetailsService = flashModelDetailsService;
        this.appPasswordConfig = appPasswordConfig;
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

        flashModelDetailsService.save(flashModel);

        //redirect to startpage to prevent duplicate submissions
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String displayAdmin(Model theModel){

        theModel.addAttribute("flashes", flashModelDetailsService.findAll());

        return "adminPage";
    }

    @GetMapping("/find/{username}")
    @ResponseBody
    public FlashModel findByUsername(@PathVariable String username) {

        System.out.println(flashModelDetailsService.loadUserByUsername(username));

        return flashModelDetailsService.loadUserByUsername(username);
    }

    @GetMapping()
    public FlashModel findById(@RequestParam("id")Long id) {
        return flashModelDetailsService.findById(id);
    }

    @GetMapping ("/delete")
    public String delete(@RequestParam("id") Long id){
        flashModelDetailsService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/flash")
    public String displayFlashPage(){

        return "flashPage";
    }


    @GetMapping("/update")
    public String updateInfo(@RequestParam("id")Long id, Model model){

        FlashModel theFlashModel = flashModelDetailsService.findById(id);

        model.addAttribute("flash", theFlashModel);

        return "editFlash";
    }

    @PostMapping("save")
    public String saveInfo(@ModelAttribute("flash") FlashModel theFlashModel){

        theFlashModel.setPassword(theFlashModel.getPassword());
        theFlashModel.setAccountNonExpired(true);
        theFlashModel.setAccountNonLocked(true);
        theFlashModel.setCredentialsNonExpired(true);
        theFlashModel.setEnabled(true);

        flashModelDetailsService.save(theFlashModel);

        return"flashPage";
    }

}

