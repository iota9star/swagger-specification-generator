package star.iota.swagger.specification.generator.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Resp {
    /**
     * @return 响应的code
     */
    String code() default "";

    /**
     * Required. A short description of the response. GFM syntax can be used for rich text representation.
     *
     * @return 响应code描述
     */
    String desc() default "";

    /**
     * A definition of the response structure. It can be a primitive, an array or an object. If this field does not exist, it means no content is returned as part of the response. As an extension to the Schema Object, its root type value may also be "file". This SHOULD be accompanied by a relevant produces mime-type.
     *
     * @return 响应的数据
     */
    Schema schema() default @Schema;

    /**
     * A list of headers that are sent with the response.
     *
     * @return 响应头
     */
    Header[] headers() default {};

    /**
     * An example of the response message.
     *
     * @return 响应消息示例
     */
    Example[] examples() default {};
}
