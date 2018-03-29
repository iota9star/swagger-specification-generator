package star.iota.swagger.specification.generator.core;

import star.iota.swagger.specification.generator.base.CollectionFormat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Header {
    String val();

    String desc() default "";

    String type();

    String format() default "";

    CollectionFormat collectionFormat() default CollectionFormat.CSV;

    String def() default "";

    String[] $enum() default {};
}
