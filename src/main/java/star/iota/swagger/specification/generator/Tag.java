package star.iota.swagger.specification.generator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Tag {
    /**
     * @return tag的名字
     */
    String name();

    /**
     * @return tag的描述
     */
    String desc() default "";

    /**
     * @return tag额外的文档
     */
    ExtDoc externalDocs() default @ExtDoc;
}
