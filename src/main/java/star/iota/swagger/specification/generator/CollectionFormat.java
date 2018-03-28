package star.iota.swagger.specification.generator;

public enum CollectionFormat {
    CSV("csv"),
    SSV("ssv"),
    TSV("tsv"),
    PIPES("pipes");
    private String format;

    public String format() {
        return format;
    }

    CollectionFormat(String format) {
        this.format = format;
    }
}
