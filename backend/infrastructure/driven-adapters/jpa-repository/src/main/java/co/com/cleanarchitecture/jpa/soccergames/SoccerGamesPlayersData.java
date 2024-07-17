package co.com.cleanarchitecture.jpa.soccergames;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "soccer_games_players")
public class SoccerGamesPlayersData implements Serializable {

    @EmbeddedId
    SoccerGamesPlayersId soccerGamesPlayersId;
    
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    //other properties
}
