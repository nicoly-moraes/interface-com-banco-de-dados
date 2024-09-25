package org.example.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cursos {
    private Long codigo;
    private String nome;
    private String sigla;
    private Area area;
}
