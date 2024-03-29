package itm.note;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;

@Data
@Service
public class NoteService {

    private final NoteRepository repository;

    @Autowired
    public NoteService(NoteRepository repository) {
        this.repository = repository;

        int i=0;
        while ( ++i <= 5 ) this.add( genRandomNote()) ;
    }

    public List<Note> listAll() {
        return repository.findAll();
    }

    public Note add(Note note) {
        return repository.saveAndFlush(note);
    }

    public void deleteById(long id) throws NoteNotFoundException {
        try {
            repository.deleteById(id);
        } catch (NullPointerException ex) { throw new NoteNotFoundException(id); }
    }

    public void update(Note note) throws NoteNotFoundException {
        if ( ! repository.existsById(note.getId()) ) throw new NoteNotFoundException(note.getId());
        else repository.save(note);
    }

    public Note getById(long id) throws NoteNotFoundException {
        return repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    private Note genRandomNote() {
        Note note = new Note();
        note.setTitle(RandomStringUtils.randomAlphabetic(5));
        note.setContent(RandomStringUtils.randomAlphabetic(20));
        return note;
    }
}
