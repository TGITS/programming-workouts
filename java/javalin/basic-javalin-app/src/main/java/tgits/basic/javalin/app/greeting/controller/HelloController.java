package tgits.basic.javalin.app.greeting.controller;

import io.javalin.http.Context;
import tgits.basic.javalin.app.greeting.Greeting;

public class HelloController {
    public void getSimpleHello(Context ctx) {
        ctx.result("Hello");
    }

    public void getNamedHello(Context ctx) {
        ctx.result("Hello " + ctx.pathParam("name"));
    }

    public void getJsonHello(Context ctx) {
        Greeting greeting = Greeting.builder().name("Hola").lang("es").build();
        ctx.json(greeting);
    }
}
