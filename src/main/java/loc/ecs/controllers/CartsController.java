package loc.ecs.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import loc.ecs.entity.Carts;
import loc.ecs.service.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = "application/json")
    public List<Carts> carts() {
        return cartsService.getCart(1);
    }

}
