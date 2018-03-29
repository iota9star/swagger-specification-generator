package star.iota.swagger.specification.generator.core;

import star.iota.swagger.specification.generator.base.CollectionFormat;
import star.iota.swagger.specification.generator.base.DataType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Items {
    DataType type() default DataType.STRING;

    CollectionFormat collectionFormat() default CollectionFormat.CSV;

    String def() default "";

    String[] $enum() default {};
}
