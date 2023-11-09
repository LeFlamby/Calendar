package com.example.demo.Style;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StyleService {

    private final StyleRepository repository;
    public List<Style> getAll(){
        return repository.findAll();
    }

    public Style getById(Long id){
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("not found" + id));
    }

    public Style create(Style style){
        return repository.save(style);
    }

    public Style update(Long id, Style style) {
        Style styleToUpdate = getById(id);
        styleToUpdate.setBackgroundColor(style.getBackgroundColor());
        styleToUpdate.setTextColor(style.getTextColor());
        styleToUpdate.setBorderColor(style.getBorderColor());
        return repository.save(styleToUpdate);
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }
}
