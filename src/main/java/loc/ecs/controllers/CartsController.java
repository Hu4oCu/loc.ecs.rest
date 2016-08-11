package loc.ecs.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import loc.ecs.entity.Carts;
import loc.ecs.service.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartsController {
    @Autowired
    private CartsService cartsService;


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

}
