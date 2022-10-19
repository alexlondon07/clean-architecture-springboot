package co.com.cleanarchitecture.model.typeidentification;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TypeDocIdentification {

    private Long id;

    private String abbreviation;

    public Date createdAt;

    private Date updatedAt;
}
