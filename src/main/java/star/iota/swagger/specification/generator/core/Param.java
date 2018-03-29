package star.iota.swagger.specification.generator.core;

import star.iota.swagger.specification.generator.base.CollectionFormat;
import star.iota.swagger.specification.generator.base.DataType;
import star.iota.swagger.specification.generator.base.In;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Param {


    /**
     * Required. The name of the parameter. Param names are case sensitive.
     * If in is "path", the name field MUST correspond to the associated path segment from the path field in the Paths Object. See Path Templating for further information.
     * For all other cases, the name corresponds to the parameter name used based on the in property.
     * 参数名
     */
    String name() default "";

    /**
     * Required. The location of the parameter. Possible values are "query", "header", "path", "formData" or "body".
     *
     * @return 输入参数类型：query|body|path|header|formData 默认query
     */
    In in() default In.QUERY;

    /**
     * A brief description of the parameter. This could contain examples of use. GFM syntax can be used for rich text representation.
     *
     * @return 参数描述
     */
    String desc() default "";

    /**
     * Determines whether this parameter is mandatory. If the parameter is in "path", this property is required and its value MUST be true. Otherwise, the property MAY be included and its default value is false.
     *
     * @return 该参数是否必要，默认false
     */
    boolean required() default false;

    /**
     * 请求参数的数据类型（type）和格式（format）
     */
    DataType type() default DataType.STRING;

    /**
     * The schema defining the type used for the body parameter.
     *
     * @return 引用的数据
     */
    Schema schema() default @Schema;

    boolean empty() default false;

    Items items() default @Items;

    String def() default "";

    CollectionFormat collectionFormat() default CollectionFormat.CSV;
}
