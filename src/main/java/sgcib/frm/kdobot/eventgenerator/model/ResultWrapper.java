package sgcib.frm.kdobot.eventgenerator.model;

public class ResultWrapper<T> {
    private final T result;
    private final String status; // OK, ERROR
    private final String message;

    public ResultWrapper(T result, String status, String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
