package star.iota.swagger.specification.generator.core;

import star.iota.swagger.specification.generator.base.MIME;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Example {
    MIME mime() default MIME.APPLICATION$JSON;
}
