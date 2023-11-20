package co.com.cleanarchitecture.model.player;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Player implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String position;
    private String cellphone;
}
