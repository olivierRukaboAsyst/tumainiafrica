package anubis.lab.anubisproject.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Response<T> {

    private Boolean success;
    private String message;
    private T result;

    public Response() {
    }

    public Response(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(Boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public Response(Boolean success, T result) {

        this.success = success;
        this.result = result;

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
