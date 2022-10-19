package co.com.cleanarchitecture.api.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.com.cleanarchitecture.model.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BrandDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 90)
    private String name;

    private boolean enable;

    public Brand convertToEntity(BrandDTO data) {
        return Brand.builder()
                .id(id)
                .name(data.getName().trim())
                .enable(data.isEnable())
                .build();
    }
}
