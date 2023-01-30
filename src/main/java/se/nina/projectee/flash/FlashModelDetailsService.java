package se.nina.projectee.flash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashModelDetailsService{

    private final FlashModelRepository flashModelRepository;

    @Autowired
    public FlashModelDetailsService(FlashModelRepository flashModelRepository) {
        this.flashModelRepository = flashModelRepository;
    }
}
