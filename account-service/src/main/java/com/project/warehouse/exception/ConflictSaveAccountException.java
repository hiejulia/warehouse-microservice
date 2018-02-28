package com.project.warehouse.exception;



import com.project.warehouse.event.model.SaveAccountErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictSaveAccountException extends AbstractAccountException {

    public static final String DEFAULT_MESSAGE  = "error during the saving process: we have already saved the entity";
    public ConflictSaveAccountException(SaveAccountErrorEvent event, String msg) {
        super(event, msg);
    }

    public ConflictSaveAccountException(SaveAccountErrorEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}