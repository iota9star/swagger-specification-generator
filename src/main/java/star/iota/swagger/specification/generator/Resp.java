package star.iota.swagger.specification.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
}
