package se.nina.projectee.flash.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.nina.projectee.flash.FlashModel;
import se.nina.projectee.flash.FlashModelRepository;

import java.util.List;
import java.util.Optional;

//implementering av queries
@Component
public class FlashModelDAO implements IFlashModelDAO<FlashModel>{

    private final FlashModelRepository flashModelRepository;

    @Autowired
    public FlashModelDAO(FlashModelRepository flashModelRepository) {
        this.flashModelRepository = flashModelRepository;
    }
    @Override
    public FlashModel findUser(String username) {
        return flashModelRepository.findByUsername(username);
    }

    @Override
    public void save(FlashModel flashModel) {
        flashModelRepository.save(flashModel);
    }

    @Override
    public void update(FlashModel flashModel, Long id) {

    }

    @Override
    public void delete(Long id) {
    flashModelRepository.deleteById(id);
    }

    @Override
    public List<FlashModel> findAll() {
        return flashModelRepository.findAll();
    }

    @Override
    public FlashModel findById(Long id){

        Optional<FlashModel> result = flashModelRepository.findById(id);

        FlashModel foundFlash;

        if (result.isPresent()){
         foundFlash=result.get();
        }else{
            throw new RuntimeException("Did not find id" + id);
        }

        return foundFlash;
    }
}