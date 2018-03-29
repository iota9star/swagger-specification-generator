package star.iota.swagger.specification.generator.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Def {
    String val();

    String type() default "object";

    DefParam[] params() default {};

    String discriminator() default "";

    String $ref() default "";

    String[] required() default {};

    String desc() default "";
}
