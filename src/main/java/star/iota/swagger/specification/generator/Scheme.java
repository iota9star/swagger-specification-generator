package star.iota.swagger.specification.generator;

public enum Scheme {
    HTTP("http"),
    HTTPS("https"),
    WS("ws"),
    WSS("wss");
    private String scheme;

    public String scheme() {
        return scheme;
    }

    Scheme(String scheme) {
        this.scheme = scheme;
    }
}
