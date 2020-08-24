package tgits.basic.javalin.app.greeting.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class WelcomeHandler implements Handler {
    @Override
    public void handle(Context ctx) {
        ctx.result("Welcome");
    }
}
