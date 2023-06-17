package dk.dtu.compute.se.pisd.roborally.client;

import com.google.gson.Gson;
import dk.dtu.compute.se.pisd.roborally.JSON.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import dk.dtu.compute.se.pisd.roborally.model.Board;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.parseBoolean;


public class ProductClient implements IBoardTemplate {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();







    public static boolean updatePlayerJoinedCounter(String p) {
        try{
            String productJSON =p;
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(productJSON))
                    .uri(URI.create("http://10.209.211.14:8080/updatePlayerJoinedCounter/"))
                    .setHeader("User-Agent", "Product Client")
                    .header("Content-Type", "application/json")
                    .build();
            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
            System.out.println("Big "+ p);
            return result.equals("added")? true : false;
        } catch (Exception e) {
            System.out.println("Great Failure, lort");
            return false;
        }
    }
    public static String getPlayerCounter() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://10.209.211.14:8080/getPlayerCounter"))
                    .setHeader("User-Agent", "Product Client")
                    .build();

            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
            System.out.println("Retrieved counter " + result);
            return result;

        } catch (Exception e) {
            System.out.println("Failed counter: " + e.getMessage());
            return null;
        }
    }
    public static boolean setPlayerTurn(String p) {
        try{
            String productJSON =p;
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(productJSON))
                    .uri(URI.create("http://10.209.211.14:8080/setPlayerTurn/"))
                    .setHeader("User-Agent", "Product Client")
                    .header("Content-Type", "application/json")
                    .build();
            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
            System.out.println("Giga Chad succesfull "+ p);
            return result.equals("added")? true : false;

        } catch (Exception e) {
            System.out.println("Great Failure, lort");
            return false;
        }
    }
    public static String getPlayerTurn() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://10.209.211.14:8080/getPlayerTurn"))
                    .setHeader("User-Agent", "Product Client")
                    .build();

            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
            System.out.println("Retrieved max players: " + result);
            return result;

        } catch (Exception e) {
            System.out.println("Failed to retrieve max players: " + e.getMessage());
            return null;
        }
    }


    public static boolean saveBoard(BoardTemplate boardTemplate, String name) {
        try{
            String url = "http://10.209.211.14:8080/saveGame/"+name;
            String productJSON = new Gson().toJson(boardTemplate);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(productJSON))
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .build();
            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
            return result.equals("added")? true : false;
        } catch (Exception e) {
            return false;
        }

    }
    public static BoardTemplate loadBoard(String name) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://10.209.211.14:8080/loadGame/" + name))
                    .setHeader("User-Agent", "Product Client")
                    .header("Content-Type", "application/json")
                    .build();
            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
            Gson gson = new Gson();
            BoardTemplate boardTemplate = gson.fromJson(result, BoardTemplate.class);
            return boardTemplate;
        } catch (Exception e) {
            return null;
        }
    }







}


