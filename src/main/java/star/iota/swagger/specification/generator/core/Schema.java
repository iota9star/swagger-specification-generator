package star.iota.swagger.specification.generator.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Schema {
    String type() default "object";

    String $ref() default "";
}
