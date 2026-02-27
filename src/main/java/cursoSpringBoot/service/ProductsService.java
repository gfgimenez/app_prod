package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;

import java.io.IOException;
import java.util.List;

public interface ProductsService {

    List<Product> getProducts() throws IOException;
}
