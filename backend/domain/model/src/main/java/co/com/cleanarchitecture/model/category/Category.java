package co.com.cleanarchitecture.model.category;

import java.io.Serializable;

import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private Long id;
    private String name;
    private String groupName;
    private boolean enable;
}
