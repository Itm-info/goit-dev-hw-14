package itm.note;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Why does it not work?
    private Long id;

    private String title;

    private String content;
}
