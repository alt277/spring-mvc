package com.geekbrains.spring.mvc.repositories;
import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1l,"Galaxys20+","phone","Samsung",67000));
        this.products.add(new Product(2L,"Galaxy_Fold2+","phone","Samsung",159000));
        this.products.add(new Product( 3L,"Iphone_12","phone","Apple",105000));
        this.products.add(new Product(4L,"Ipone_11","phone","Samsung",55000));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product saveOrUpdate(Product product) {
        if ( product.getId() == null) {
            product.setId(products.size()+1L);
            products.add(product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Error save or update product");
    }

    public Product findById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new RuntimeException("This product not founded");
    }
}
