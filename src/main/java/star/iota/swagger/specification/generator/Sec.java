package star.iota.swagger.specification.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
public @interface Sec {
    /**
     * @return 安全方案的名称
     */
    String key() default "";

    /**
     * @return 安全方案对应的值
     */
    String[] values() default {};
}
