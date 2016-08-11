package loc.ecs.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import loc.ecs.entity.Carts;
import loc.ecs.service.CartsService;
import loc.ecs.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartsController {
    @Autowired
    private CartsService cartsService;

    @Autowired
    private ProductsService productsService;


    @JsonView(Carts.Extended.class)
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Carts> carts() {
        return cartsService.getCart(1);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String count() {
        Integer productCount = cartsService.getCart(1).size();
        return "{\"count\":" + productCount.toString() + "}";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addToCart(@RequestParam(name = "pid") Integer pid) {
        Carts newItem = new Carts(1, pid, 1);
        newItem.setProduct(productsService.getProductsById(pid));
        System.out.println("trying to add product with pid=" + pid);
        cartsService.addtocart(newItem);
    }

}
