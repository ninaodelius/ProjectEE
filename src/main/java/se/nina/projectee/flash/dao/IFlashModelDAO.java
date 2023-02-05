package se.nina.projectee.flash.dao;

import se.nina.projectee.flash.FlashModel;

import java.util.List;

public interface IFlashModelDAO<T> {

    FlashModel findUser(String username);
    void save(FlashModel flashmodel);
    void update(T t, Long l);
    void delete(Long l);

    List<FlashModel> findAll();
    FlashModel findById(Long id);
}