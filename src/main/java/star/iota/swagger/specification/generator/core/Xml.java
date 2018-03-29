package star.iota.swagger.specification.generator.core;

import star.iota.swagger.specification.generator.kv.BooleanKV;
import star.iota.swagger.specification.generator.kv.StringKV;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Xml {
    StringKV[] attr() default {};

    BooleanKV[] booleanAttr() default {};
}
