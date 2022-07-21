package olzhas106.jpa.lesson.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "specifications")
public class Specification {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;

    private String name;

    @OneToMany (mappedBy = "specification")
    private List<SpecValue> specValues;

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<SpecValue> getSpecValues() {
        return specValues;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecValues(List<SpecValue> specValues) {
        this.specValues = specValues;
    }
}

