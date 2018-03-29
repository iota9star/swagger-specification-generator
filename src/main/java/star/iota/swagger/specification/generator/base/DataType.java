
package star.iota.swagger.specification.generator.base;


public enum DataType {
    /**
     * signed 32 bits
     */
    INTEGER("integer", "int32"),
    /**
     * signed 64 bits
     */
    LONG("integer", "int64"),

    FLOAT("number", "float"),

    DOUBLE("number", "double"),

    STRING("string", null),
    /**
     * base64 encoded characters
     */
    BYTE("string", "byte"),
    /**
     * any sequence of octets
     */
    BINARY("string", "binary"),

    BOOLEAN("boolean", null),
    /**
     * As defined by full-date - RFC3339
     */
    DATE("string", "date"),
    /**
     * As defined by date-time - RFC3339
     */
    DATE_TIME("string", "date-time"),
    /**
     * A hint to UIs to obscure input.
     */
    PASSWORD("string", "password"),

    /**
     * 针对返回类型为数组的
     */
    ARRAY("ARRAY", null),
    /**
     * 返回类型为文件的接口
     */
    FILE("file", null),

    OTHER("other", null);

    private String type;

    public String type() {
        return type;
    }

    private String format;

    public String format() {
        return format;
    }

    DataType(String type, String format) {
        this.type = type;
        this.format = format;
    }
}
