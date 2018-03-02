package com.project.warehouse.exception;



import com.project.warehouse.event.model.AbstractDomainEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductsNotFoundException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "Products didn't found";

    public ProductsNotFoundException(AbstractDomainEvent event, String msg) {
        super(event, msg);
    }

    public ProductsNotFoundException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}
