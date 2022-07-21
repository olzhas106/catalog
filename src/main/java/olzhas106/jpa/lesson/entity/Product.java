package olzhas106.jpa.lesson.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;

    private String name;

    private Integer price;

    @OneToMany (mappedBy = "product", cascade = CascadeType.REMOVE)
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

    public Integer getPrice() {
        return price;
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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setSpecValues(List<SpecValue> specValues) {
        this.specValues = specValues;
    }
}
