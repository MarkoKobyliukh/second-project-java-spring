package pl.edu.vistula.second_project_java_spring.product.support.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super(String.format("Product with %d not found", id));
    }
}
