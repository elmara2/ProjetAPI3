package be.condorcet.projetapi3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "COURS", schema = "ORA10" , catalog = "orcl")
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "classe_generator")
    @SequenceGenerator(name = "classe_generator", sequenceName = "MASEQUENCE",allocationSize = 1)
    private Integer idcours;
    @NonNull
    private String code;
    private String intitule;
    @JsonIgnore
    @OneToMany(mappedBy = "cours")
    @ToString.Exclude
    private List<Infos> listInfos;

}
