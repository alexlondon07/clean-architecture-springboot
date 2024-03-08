package co.com.cleanarchitecture.api.dto;

import co.com.cleanarchitecture.model.player.Player;
import co.com.cleanarchitecture.model.soccergames.SoccerGames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SoccerGamesDTO implements Serializable {
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    @Size(max = 15)
    private String time;

    @NotNull
    private String field;

    @NotNull
    private String fieldNumber;

    @NotNull
    private Double price;

    private int playerNumber;

    private String description;

    @NotNull
    private Set<Player> players;

    private String status;

    private boolean enable = true;

    private Date createdAt;

    private Date updatedAt;

    public SoccerGames convertToEntity(SoccerGamesDTO data) {
        return SoccerGames.builder()
                .id(id)
                .date(data.getDate())
                .time(data.getTime())
                .field(data.getField())
                .fieldNumber(data.getFieldNumber())
                .price(data.getPrice())
                .playerNumber(data.getPlayerNumber())
                .description(data.getDescription())
                .players(data.getPlayers())
                .enable(data.isEnable())
                .status(data.getStatus())
                .build();
    }
}
