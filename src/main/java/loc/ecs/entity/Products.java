package loc.ecs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ecs_products")
public class Products implements Serializable {
    @JsonView(value = Carts.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "products_seq_gen")
    @SequenceGenerator(name = "products_seq_gen", sequenceName = "product_id_seq")
    @Column(name = "product_id", nullable = false)
    private int product_id;
    @JsonView(value = Carts.Public.class)
    @Column(name = "product_name", nullable = false)
    private String name;
    @JsonView(value = Carts.Public.class)
    @Column(name = "description", nullable = false)
    private String description;
    @JsonView(value = Carts.Public.class)
    @Column(name = "price", nullable = false)
    private int price;
    @JsonView(value = Carts.Public.class)
    @Column(name = "image", nullable = false)
    private String image;

    @OneToOne(mappedBy = "product")
    private Carts carts;

    @JsonIgnore
    public Carts getCarts() {
        return carts;
    }

    public void setCarts(Carts carts) {
        this.carts = carts;
    }

    protected Products() {}

    public Products(int product_id, String name, String description, int price, String image) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
