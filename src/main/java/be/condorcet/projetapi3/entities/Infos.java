package be.condorcet.projetapi3.entities;

import lombok.*;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "INFOS", schema = "ORA10" , catalog = "orcl")

public class Infos {

    @EmbeddedId
    private InfosPK id_infos;

    @Column(name = "nbreheures")
    private Integer nbreheures;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDCLASSE",insertable = false, updatable = false)
    private Classe classe;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "IDCOURS",insertable = false,updatable = false)
    private Cours cours;


    @ManyToOne
    @JoinColumn(name = "IDENSEIGNANT")
    private Enseignant enseignant;


    @ManyToOne
    @JoinColumn(name = "IDSALLE")
    private Salle salle;

}
