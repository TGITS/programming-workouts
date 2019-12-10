package tgits.opentracingsimplepoc.hello;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hello {
    private final long id;
    private final String content;
}
