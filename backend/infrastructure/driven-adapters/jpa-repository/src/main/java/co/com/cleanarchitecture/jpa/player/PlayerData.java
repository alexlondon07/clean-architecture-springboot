package co.com.cleanarchitecture.jpa.player;

import javax.persistence.*;

import co.com.cleanarchitecture.jpa.position.PositionData;
import co.com.cleanarchitecture.model.player.Player;
import co.com.cleanarchitecture.model.position.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "players")
public class PlayerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 155, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "position_id")
    private PositionData position;

    @Column(length = 12, nullable = false, unique = true)
    private String cellphone;

    @Column(length = 120)
    private String photo;

    public static Player getPlayerFromPlayerData(PlayerData playerData) {

        if (playerData==null)
            return null;

        Position position = Position.builder()
                .id(playerData.getPosition().getId())
                .position(playerData.getPosition().getPosition())
                .abbreviation(playerData.getPosition().getAbbreviation()).build();

        return Player.builder()
                .id(playerData.getId())
                .name(playerData.getName())
                .position(position)
                .cellphone(playerData.getCellphone())
                .photo(playerData.getPhoto())
                .build();
    }
}
