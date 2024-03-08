package co.com.cleanarchitecture.model.soccergames;

import co.com.cleanarchitecture.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SoccerGames {
    private Long id;
    private Date date;
    private String time;
    private String field;
    private String fieldNumber;
    private Double price;
    private int playerNumber;
    private String description;
    private Set<Player> players;
    private String status;
    private boolean enable = true;
}

