package se.nina.projectee.flash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashModelDetailsService implements UserDetailsService {

    private final FlashModelRepository flashModelRepository;

    @Autowired
    public FlashModelDetailsService(FlashModelRepository flashModelRepository) {
        this.flashModelRepository = flashModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return flashModelRepository.findByUsername(username);
    }

    public void save(FlashModel flashModel){
        flashModelRepository.save(flashModel);
    }

    public List<FlashModel> findAll(){
    return flashModelRepository.findAll();
    }
}
