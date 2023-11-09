package com.example.demo.Note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public List<Note> getAll() {

            return repository.findAll();
        }


    public Note getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found" + id));
    }

    public Note create(Note note) {
return repository.save(note);
    }

    public Note update(Long id, Note note) {
        Note noteToUpdate = getById(id);
        noteToUpdate.setContent(note.getContent());
        noteToUpdate.setDate(note.getDate());
        return repository.save(noteToUpdate);
    }

    public void delete(Long id) {
        repository.deleteById(id);

    }
}
