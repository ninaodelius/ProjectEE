package se.nina.projectee.flash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.nina.projectee.flash.dao.FlashModelDAO;

import java.util.List;

@Service
public class FlashModelDetailsService implements UserDetailsService {

    private final FlashModelDAO flashModelDAO;

    @Autowired
    public FlashModelDetailsService(FlashModelDAO flashModelDAO) {
        this.flashModelDAO = flashModelDAO;
    }

    @Override
    public FlashModel loadUserByUsername(String username) throws UsernameNotFoundException {
        return flashModelDAO.findUser(username);
    }


    public void save(FlashModel flashModel){
        flashModelDAO.save(flashModel);
    }

    public List<FlashModel> findAll(){
    return flashModelDAO.findAll();
    }

    /*
    private FlashModelDTO convertDataIntoDTO(FlashModel flashModel){
        return new FlashModelDTO(flashModel);
    }*/

    public void deleteById(Long id){
        flashModelDAO.delete(id);
    }

    public FlashModel findById(Long id){

        return flashModelDAO.findById(id);
    }




}
