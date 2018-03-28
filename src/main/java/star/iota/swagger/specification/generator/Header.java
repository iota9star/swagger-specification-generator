package star.iota.swagger.specification.generator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Header {
    String desc() default "";

    String type();

    String format() default "";

    CollectionFormat collectionFormat() default CollectionFormat.CSV;

}
