package star.iota.swagger.specification.generator.kv;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface StringKV {
    String key();

    String val();
}
