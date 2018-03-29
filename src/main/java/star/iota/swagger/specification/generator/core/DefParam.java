package star.iota.swagger.specification.generator.core;

import star.iota.swagger.specification.generator.base.DataType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface DefParam {
    String val();

    DataType type();

    Xml xml() default @Xml;
}
