package pl.edu.vistula.second_project_java_spring.product.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UpdateProductRequest extends ProductRequest{

    private final Long id;

    @JsonCreator
    public UpdateProductRequest(String name, Long id){
        super(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
