package olzhas106.jpa.lesson.entity;

import javax.persistence.*;

@Entity
@Table (name = "spec_values")
public class SpecValue {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn (name = "specifications_id")
    private Specification specification;

    private String value;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Specification getSpecification() {
        return specification;
    }

    public String getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
