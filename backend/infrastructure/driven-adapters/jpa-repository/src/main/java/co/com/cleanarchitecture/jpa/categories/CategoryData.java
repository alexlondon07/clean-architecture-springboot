package co.com.cleanarchitecture.jpa.categories;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import co.com.cleanarchitecture.jpa.user.UserData;
import co.com.cleanarchitecture.model.category.Category;
import co.com.cleanarchitecture.model.role.Role;
import co.com.cleanarchitecture.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryData  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 90, nullable = false)
    private String name;

    @Column(name = "group_name", length = 60)
    private String groupName;

    private boolean enable = true;


    public static Category getCategoryFromCategoryData(CategoryData categoryData) {

        if (categoryData==null)
            return null;

        return Category.builder()
                .id(categoryData.getId())
                .name(categoryData.getName())
                .groupName(categoryData.getGroupName())
                .enable(categoryData.isEnable())
                .build();
    }


    public static List<Category> convertCategoryDataListToCategoryList(List<CategoryData> dataList) {

        if (dataList.isEmpty())
            return new ArrayList<>();

        List<Category> arrayList = new ArrayList<>();

        dataList.forEach( data-> arrayList.add(getCategoryFromCategoryData(data)));

        return arrayList;
    }

}
