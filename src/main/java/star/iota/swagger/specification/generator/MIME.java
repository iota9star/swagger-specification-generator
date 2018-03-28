package star.iota.swagger.specification.generator;

public enum MIME {
    X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
    FORM_DATA("multipart/form-data"),
    HTML("text/html"),
    JSON("application/json"),
    XML("application/xml");
    private String mime;

    public String mime() {
        return mime;
    }

    MIME(String mime) {
        this.mime = mime;
    }
}
