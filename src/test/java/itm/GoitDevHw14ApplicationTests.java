package itm;

import itm.note.Note;
import itm.note.NoteNotFoundException;
import itm.note.NoteRepository;
import itm.note.NoteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GoitDevHw14ApplicationTests {
	private NoteService noteService;

	@BeforeEach
	public void beforeEach(@Autowired NoteRepository repository) {
		noteService = new NoteService(repository);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void noteAddTest() {
		Note note = new Note();
		note.setTitle("Title_1");
		note.setContent("Content_1");
		Note addedNote = noteService.add(note);
		Assertions.assertEquals(note, addedNote);
	}

	@Test
	void deleteByIdTest() {
		Note note = new Note();
		noteService.add(note);
		Assertions.assertDoesNotThrow(()->noteService.deleteById(note.getId()));
	}

	@Test
	void noteUpdateTest() throws NoteNotFoundException {
		Note note = new Note();
		note.setTitle("Title_1");
		note.setContent("Content_1");
		noteService.add(note);

		Note newNote = new Note();
		newNote.setTitle("newTitle");
		newNote.setContent("newContent");
		newNote.setId(note.getId());
		noteService.update(newNote);
		Assertions.assertEquals(newNote, noteService.getById(note.getId()));
	}

	@Test
	void getByIdTest() throws NoteNotFoundException {
		Note note = new Note();
		note.setTitle("Title_1");
		note.setContent("Content_1");
		noteService.add(note);
		Note gotNote = noteService.getById(note.getId());
		Assertions.assertEquals(note, gotNote);
	}

	@Test
	void listAllTest() {
		int initAutoGeneratedNotesNumber = 5;
		Note note1 = new Note(), note2 = new Note();
		noteService.add(note1);
		noteService.add(note2);

		List<Note> list = noteService.listAll();
		Assertions.assertEquals(initAutoGeneratedNotesNumber + 2, list.size());
	}

	@AfterEach
	void clearRepo() {
		noteService.getRepository().deleteAll();
	}
}
