package co.com.cleanarchitecture.model.position;

import lombok.*;

import java.io.Serializable;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position implements Serializable {
    private Long id;
    private String position;
    private String description;
}
