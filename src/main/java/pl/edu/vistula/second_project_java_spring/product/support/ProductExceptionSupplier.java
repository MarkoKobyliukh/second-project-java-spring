package pl.edu.vistula.second_project_java_spring.product.support;

import pl.edu.vistula.second_project_java_spring.product.support.exception.ProductNotFoundException;

import java.util.function.Supplier;

public class ProductExceptionSupplier {

    public static Supplier<ProductNotFoundException> productNotFound(Long id){
        return () -> new ProductNotFoundException(id);
    }
}
