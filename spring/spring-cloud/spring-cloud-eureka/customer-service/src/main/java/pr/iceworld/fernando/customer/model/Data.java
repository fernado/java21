package pr.iceworld.fernando.customer.model;

public class Data<T> {

    private String code;
    private T data;
    private String message;

    private Data(String code, String message) {
        this(code, null, message);
    }

    private Data(String code, T data) {
        this(code, data, null);
    }

    private Data(String code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> Data success(T data) {
        return new Data<>("200", data);
    }

    public static <T> Data failure(String code, String message) {
        return new Data<>(code, message);
    }
}
