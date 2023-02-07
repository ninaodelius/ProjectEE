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
import se.nina.projectee.flash.weatherapi.WeatherWebClient;

@Controller
//@CrossOrigin(value = "localhost:3000")
public class AppController {


    private final FlashModelDetailsService flashModelDetailsService;
    private final AppPasswordConfig appPasswordConfig;
    private final WeatherWebClient weatherWebClient;


    public AppController(FlashModelDetailsService flashModelDetailsService, AppPasswordConfig appPasswordConfig, WeatherWebClient weatherWebClient) {
        this.flashModelDetailsService = flashModelDetailsService;
        this.appPasswordConfig = appPasswordConfig;
        this.weatherWebClient = weatherWebClient;
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


    @GetMapping("/showUpdate")
    public String showInfo(@RequestParam("id")Long id, Model model){

        FlashModel theFlashModel = flashModelDetailsService.findById(id);

        model.addAttribute("flash", theFlashModel);

        return "editFlash";
    }

    @PostMapping("/updateById")
    public String saveInfo(@ModelAttribute("flash") FlashModel theFlashModel, Long id){

        FlashModel userToUpdate = findById(id);

        if (theFlashModel.getName() != null) {userToUpdate.setName(theFlashModel.getName());}
        if (theFlashModel.getUsername() != null) {userToUpdate.setUsername(theFlashModel.getUsername());}
        if (theFlashModel.getPassword() != null) {userToUpdate.setPassword(theFlashModel.getPassword());}

        userToUpdate.setAccountNonExpired(true);
        userToUpdate.setAccountNonLocked(true);
        userToUpdate.setCredentialsNonExpired(true);
        userToUpdate.setEnabled(true);

        flashModelDetailsService.save(userToUpdate);

        return"flashPage";
    }

    @GetMapping("/fetchWeather")
    public String fetchWeather(Model model){

        model.addAttribute("weather", weatherWebClient.fluxToList());

        return "weather";
    }

    @GetMapping("/")
    public String showStartPage(Model model){
        model.addAttribute("weather", weatherWebClient.fluxToList());
        return "startpage";
    }

}

