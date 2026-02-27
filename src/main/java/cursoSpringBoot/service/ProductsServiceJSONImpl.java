package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

//@Primary
@Service
@ConditionalOnProperty(name = "service.products", havingValue = "json")
public class ProductsServiceJSONImpl implements ProductsService{

    @Override
    public List<Product> getProducts() {

        List<Product> products;

        try {
            products= new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/products.json")
                            , new TypeReference<List<Product>>() {});
            return products;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //return null;
    }
}
