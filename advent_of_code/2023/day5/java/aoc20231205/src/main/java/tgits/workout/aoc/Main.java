package tgits.workout.aoc;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    // Advent of Code: Fetching Puzzle Input using the API : https://mmhaskell.com/blog/2023/1/30/advent-of-code-fetching-puzzle-input-using-the-api
    // Advent Of Code API : https://aoc.fornwall.net/api/
    private static final String API_SERVER = "https://advent.fly.dev";
    //private static String API_SERVER = "https://aoc.fornwall.workers.dev";
    //private static String API_SERVER = "https://mystifying-blackwell-9e705f.netlify.app";

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String year = "2023";
        String day = "5";
        String input_text_url = "https://adventofcode.com/" + year + "/day/" + day + "/input";
        System.out.println("input text url: " + input_text_url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(input_text_url))
                .GET()
                .build();
        try(HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println();
            System.out.println("------");
            System.out.println(response.body());
            System.out.println("------");

        }
    }
}