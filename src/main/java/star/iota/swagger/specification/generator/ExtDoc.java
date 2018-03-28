package star.iota.swagger.specification.generator;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
