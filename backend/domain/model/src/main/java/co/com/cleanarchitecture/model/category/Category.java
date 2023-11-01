package co.com.cleanarchitecture.model.category;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Category implements Serializable {
    private Long id;
    private String name;
    private String groupName;
    private boolean enable;
}
