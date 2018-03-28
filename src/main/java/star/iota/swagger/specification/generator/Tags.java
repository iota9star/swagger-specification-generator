package star.iota.swagger.specification.generator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Tags {
    /**
     * @return 每一个tag对应一个分组
     */
    Tag[] tag() default {};
}
