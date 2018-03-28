package star.iota.swagger.specification.generator;

public enum MIME {
    APPLICATION$X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
    MULTIPART$FORM_DATA("multipart/form-data"),
    TEXT$HTML("text/html"),
    APPLICATION$JSON("application/json"),
    APPLICATION$XML("application/xml");
    private String mime;

    public String mime() {
        return mime;
    }

    MIME(String mime) {
        this.mime = mime;
    }
}
