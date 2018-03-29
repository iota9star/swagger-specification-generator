package star.iota.swagger.specification.generator.base;

public enum Method {
    GET("get"),
    PUT("put"),
    POST("post"),
    DELETE("delete"),
    OPTIONS("options"),
    HEAD("head"),
    PATCH("patch");
    private String method;

    public String method() {
        return method;
    }

    Method(String method) {
        this.method = method;
    }
}
