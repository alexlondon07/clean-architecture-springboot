package co.com.cleanarchitecture.jpa.soccergames;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SoccerGamesPlayersId implements Serializable {

    @Column(name = "soccer_games_id")
    public Long soccerGamesId;

    @Column(name = "player_id")
    public Long playerId;
}
