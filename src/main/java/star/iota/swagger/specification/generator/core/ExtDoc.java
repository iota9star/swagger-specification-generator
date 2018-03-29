package star.iota.swagger.specification.generator.core;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ExtDoc {
    /**
     * @return 一个简短的描述目标文档
     */
    String desc() default "";

    /**
     * @return 必填参数，目标文档的url
     */
    String url() default "";
}
