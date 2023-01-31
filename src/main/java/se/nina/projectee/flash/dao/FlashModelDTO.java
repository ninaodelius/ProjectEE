package se.nina.projectee.flash.dao;

import se.nina.projectee.flash.FlashModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//used for transferring data securely
public class FlashModelDTO {

    private String username;
    private final List<String> authorities;

    public FlashModelDTO(FlashModel flashModel) {
        this.username = flashModel.getUsername();
        this.authorities = new ArrayList<>(Collections.singleton(
                String.valueOf(flashModel.getAuthorities())
        ));
    }


}