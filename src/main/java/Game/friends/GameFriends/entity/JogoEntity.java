package Game.friends.GameFriends.entity;

import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity(name = "JOGOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
public class JogoEntity {
    @Id
    @SequenceGenerator(name = "JOGOS_SEQ", sequenceName = "SEQ_JOGOS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOGOS_SEQ")
    @Column(name = "ID_JOGO")
    private Integer idJogo;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "ANO_LANCAMENTO")
    private OffsetDateTime anoLancamento;

    @Column(name = "AVG_RATING")
    private Double avgRating;

    @Column(name = "TOTAL_RATING")
    private Integer totalRating;

    @Type(type = "string-array")
    @Column(name = "GENERO", columnDefinition = "varchar(128)[]")
    private String[] genero;

    @Type(type = "string-array")
    @Column(name = "PLATAFORMAS", columnDefinition = "varchar(128)[]")
    private String[] plataformas;

    @Column(name = "PRODUTORA")
    private String produtora;

    @Column(name = "IMG")
    private String img;

    @JsonIgnore
    @OneToMany(mappedBy = "jogos", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioJogoEntity> usuarioJogos;
}
