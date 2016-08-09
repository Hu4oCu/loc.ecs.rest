package loc.ecs.controllers;

import loc.ecs.entity.Products;
import loc.ecs.service.ProductsService;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductsService productsService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Products> products() {
        return productsService.getLast10Rows();
    }
}
