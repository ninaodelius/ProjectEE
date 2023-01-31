package se.nina.projectee.flash.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.nina.projectee.flash.FlashModel;
import se.nina.projectee.flash.FlashModelRepository;

import java.util.List;

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
    public void delete(FlashModel flashModel, Long id) {

    }
    @Override
    public List<FlashModel> findAll() {
        return flashModelRepository.findAll();
    }
}