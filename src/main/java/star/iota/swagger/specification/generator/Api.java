package star.iota.swagger.specification.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Api {
    /**
     * @return api除基地址外的地址
     */
    String url();

    /**
     * @return 接口的请求方法，默认为GET
     */
    Method method() default Method.GET;

    /**
     * A list of tags for API documentation control. Tags can be used for logical grouping of operations by resources or any other qualifier.
     *
     * @return 对接口进行分组，tag名一致为一组
     */
    String[] tags() default {};

    /**
     * @return tag对应的描述
     */
    TagDesc[] tagsDesc() default {};

    /**
     * A short summary of what the operation does. For maximum readability in the swagger-ui, this field SHOULD be less than 120 characters.
     *
     * @return 接口功能的简单描述
     */
    String summary() default "";

    /**
     * A verbose explanation of the operation behavior. GFM syntax can be used for rich text representation.
     *
     * @return 接口功能的详细描述
     */
    String desc() default "";

    /**
     * Additional external documentation for this operation.
     *
     * @return 额外的外部文档操作
     */
    ExtDoc externalDocs() default @ExtDoc;

    /**
     * Unique string used to identify the operation. The id MUST be unique among all operations described in the API. Tools and libraries MAY use the operationId to uniquely identify an operation, therefore, it is recommended to follow common programming naming conventions.
     *
     * @return 唯一的id，用来标记这个接口，比如该接口为通过id查找用户信息，则可以命名为findUserInfoById
     */
    String operationId() default "";

    /**
     * A list of MIME types the operation can consume. This overrides the consumes definition at the Swagger Object. An empty value MAY be used to clear the global definition. Value MUST be as described under Mime Types.
     *
     * @return 接口请求的MIME
     */
    MIME[] consumes() default {};

    /**
     * A list of MIME types the operation can produce. This overrides the produces definition at the Swagger Object. An empty value MAY be used to clear the global definition. Value MUST be as described under Mime Types.
     *
     * @return 响应MIME，默认为application/json; charset=utf-8
     */
    MIME[] produces() default {MIME.APPLICATION$JSON};

    /**
     * A list of parameters that are applicable for this operation. If a parameter is already defined at the Path Item, the new definition will override it, but can never remove it. The list MUST NOT include duplicated parameters. A unique parameter is defined by a combination of a name and location. The list can use the Reference Object to link to parameters that are defined at the Swagger Object's parameters. There can be one "body" parameter at most.
     *
     * @return 请求的参数
     */
    Param[] parameters() default {};


    /**
     * Required. The list of possible responses as they are returned from executing this operation.
     *
     * @return 必要的，返回可能的响应
     */
    Resp[] responses() default {};

    /**
     * The transfer protocol for the operation. Values MUST be from the list: "http", "https", "ws", "wss". The value overrides the Swagger Object schemes definition.
     *
     * @return 请求协议，默认http
     */
    Scheme[] schemes() default {};

    /**
     * Declares this operation to be deprecated. Usage of the declared operation should be refrained. Default value is false.
     *
     * @return 该接口是否被废弃，默认false
     */
    boolean deprecated() default false;

    /**
     * A declaration of which security schemes are applied for this operation. The list of values describes alternative security schemes that can be used (that is, there is a logical OR between the security requirements). This definition overrides any declared top-level security. To remove a top-level security declaration, an empty array can be used.
     *
     * @return 申明这个操作应该用什么样的安全操作
     */
    Sec[] security() default {};

    /**
     * @return 用于隐藏接口，默认为false
     */
    boolean hide() default false;

    /**
     * @return 额外的信息，依照数组顺序换行拼接在接口描述下面
     */
    String[] additional() default {};

}
