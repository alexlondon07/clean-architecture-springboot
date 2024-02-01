package co.com.cleanarchitecture.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.com.cleanarchitecture.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PlayerDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 45)
    private String position;

    @NotNull
    @Size(min = 3, max = 12)
    private String cellphone;

    public Player convertToEntity(PlayerDTO data) {
        return Player.builder()
                .id(id)
                .name(data.getName().trim())
                .cellphone(data.getCellphone().trim())
                .position(data.getPosition())
                .build();
    }

}
