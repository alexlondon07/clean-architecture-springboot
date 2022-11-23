package co.com.cleanarchitecture.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import co.com.cleanarchitecture.model.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CategoryDTO implements Serializable {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 90)
    private String name;

    @NotBlank
    @Size(min = 3, max = 40)
    private String groupName;

    private boolean enable;

    public Category convertToEntity(CategoryDTO data) {
        return Category.builder()
                .id(id)
                .name(data.getName().trim())
                .groupName(data.getGroupName().trim())
                .enable(data.isEnable())
                .build();
    }
}
