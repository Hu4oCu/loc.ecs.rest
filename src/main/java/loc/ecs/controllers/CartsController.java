package loc.ecs.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import loc.ecs.entity.Carts;
import loc.ecs.service.CartsService;
import loc.ecs.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

/*    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addToCart(@RequestParam(name = "pid") Integer pid) {
        Carts newItem = new Carts(1, pid, 1);

        cartsService.addtocart(newItem);

        return "{\"value\": true}";
    }*/

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String add(@RequestBody String body) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Integer user_id = objectMapper.readTree(body).get("user_id").asInt();
        Integer product_id = objectMapper.readTree(body).get("product_id").asInt();
        Integer quantity = objectMapper.readTree(body).get("quantity").asInt();

        Carts newItem = new Carts(user_id, product_id, quantity);

        cartsService.addtocart(newItem);

        return "{\"price\":" + productsService.getProductsById(product_id).getPrice() + "," +
               "\"cart_size\":" + cartsService.getCart(1).size() + "}";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String remove(@RequestBody String body) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Integer user_id = objectMapper.readTree(body).get("user_id").asInt();
        Integer product_id = objectMapper.readTree(body).get("product_id").asInt();

        cartsService.deletePid(user_id, product_id);

        Integer product_count = cartsService.getCart(1).size();

        return product_count.toString();
    }

}
