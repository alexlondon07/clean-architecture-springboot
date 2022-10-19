package co.com.cleanarchitecture.model.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Brand {
    private Long id;
    private String name;
    private boolean enable;
}
