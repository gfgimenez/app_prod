package cursoSpringBoot.controllers;

import cursoSpringBoot.configurations.ExternalizedConfigurations;
import cursoSpringBoot.domain.Product;
import cursoSpringBoot.service.ProductsService;
import cursoSpringBoot.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

    // Inyección de Dependencia
    //ProductsService productsService=new ProductsServiceImpl();
    @Autowired
    @Lazy
    //@Qualifier("jsonResourceService")
    private ProductsService productsService;

    @Autowired
    private ExternalizedConfigurations externalizedConfigurations;

    @GetMapping
    public ResponseEntity<?> getProducts() throws IOException {

        System.out.println(externalizedConfigurations.toString());

        List<Product> products= productsService.getProducts();

        return ResponseEntity.ok(products);
    }

}
