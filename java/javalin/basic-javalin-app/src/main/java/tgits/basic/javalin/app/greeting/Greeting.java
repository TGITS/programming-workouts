package tgits.basic.javalin.app.greeting;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Greeting {
    String name;
    String lang;
}
