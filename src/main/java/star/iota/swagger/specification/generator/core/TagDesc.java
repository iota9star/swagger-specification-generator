package star.iota.swagger.specification.generator.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface TagDesc {
    /**
     * @return tag的名字
     */
    String name();

    /**
     * @return tag的描述
     */
    String desc();

    /**
     * @return 额外的文档
     */
    ExtDoc extDocs() default @ExtDoc;
}
