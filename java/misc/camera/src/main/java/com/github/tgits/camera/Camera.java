package com.github.tgits.camera;


/**
 * Example taken from Functional Programming in Java from Venkat Subramaniam,
 * Chapter 4 "Designing with Lambda Expressions"
 */

import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class Camera {
    private Function<Color, Color> filter;

    public Camera() {
        setFilters();
    }

    public Color capture(final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);
        return processedColor;
    }

    public void setFilters(final Function<Color, Color>... filters) {
        filter = Arrays.asList(filters).stream()
                .reduce((filter, next) -> filter.compose(next))
                .orElse(color -> color);
    }

    public static void main(String args[]) {
        final Camera camera = new Camera();
        final Consumer<String> printCaptured = (filterInfo) -> System.out.println(String.format("with %s: %s", filterInfo, camera.capture(new Color(200, 100, 200))));
        //printCaptured.accept("no filter");
        //camera.setFilters(Color::brighter);
        //printCaptured.accept("brighter filter");
        //camera.setFilters(Color::darker);
        //printCaptured.accept("darker filter");
        camera.setFilters(Color::brighter, Color::darker);
        printCaptured.accept("brighter & darker filter");
    }
}
