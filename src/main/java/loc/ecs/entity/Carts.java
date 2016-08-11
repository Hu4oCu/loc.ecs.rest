package loc.ecs.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ecs_cart")
public class Carts implements Serializable{


    public interface Public {};
    public interface Extended extends Public {};

    @JsonView(Carts.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq_gen")
    @SequenceGenerator(name = "cart_seq_gen", sequenceName = "cart_id_seq", allocationSize = 1)
    @Column(name = "cart_id", nullable = false, unique = true, insertable = false, updatable = false)
    private int cart_id;

    @JsonView(Carts.Public.class)
    @Column(name = "user_id")
    private int user_id;

    @JsonView(Carts.Public.class)
    @Column(name = "product_id", insertable = false, updatable = false)
    private int product_id;

    @JsonView(Carts.Public.class)
    @Column(name = "count")
    private int count;

    @JsonView(Extended.class)
    private Products product;

    @Access(AccessType.PROPERTY)
    @OneToOne(targetEntity=Products.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="product_id")
    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    protected Carts() {}

    public Carts(int uid, int product_id, int count) {
        this.user_id = uid;
        this.product_id = product_id;
        this.count = count;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}