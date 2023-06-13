package dk.dtu.compute.se.pisd.roborally.client;

import com.google.gson.Gson;
import dk.dtu.compute.se.pisd.roborally.JSON.BoardTemplate;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class ProductClient implements IBoardTemplate {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static String getProducts() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://10.209.211.14:8080/products"))
                .setHeader("User-Agent", "Product Client")
                .header("Content-Type", "application/json")
                .build();
        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
        return result;
    }

    public static String getProduct() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/products/100"))
                .setHeader("User-Agent", "Product Client")
                .header("Content-Type", "application/json")
                .build();
        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
        return result;
    }


    public Product getProductById(int id) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/products/" + id))
                    .setHeader("User-Agent", "Product Client")
                    .header("Content-Type", "application/json")
                    .build();
            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
            Gson gson = new Gson();
            Product p = gson.fromJson(result, Product.class);
            return p;
        } catch (Exception e) {
            return null;
        }
    }




    public boolean addProduct(Product p) {
        try{
            String productJSON = new Gson().toJson(p);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(productJSON))
                    .uri(URI.create("http://localhost:8080/products/"))
                    .setHeader("User-Agent", "Product Client")
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






    public static boolean saveBoard(BoardTemplate boardTemplate, String name) {
        try{
            String url = "http://localhost:8080/saveGame/"+name;
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
                    .uri(URI.create("http://localhost:8080/loadGame/" + name))
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

    public static String printTest() {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/printTest/" ))
                    .setHeader("User-Agent", "Product Client")
                    .header("Content-Type", "application/json")
                    .build();
            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
            return result;
        } catch (Exception e) {
            return null;
        }
    }



    public boolean updateProduct(int id, Product p) {
        try{
            String productJSON = new Gson().toJson(p);
            HttpRequest request = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.ofString(productJSON))
                    .uri(URI.create("http://localhost:8080/products/" + id))
                    .setHeader("User-Agent", "Product Client")
                    .header("Content-Type", "application/json")
                    .build();
            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
            return result.equals("updated")? true : false;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean deleteProductById(int id) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create("http://localhost:8080/products/" + id))
                    .setHeader("User-Agent", "Product Client")
                    .header("Content-Type", "application/json")
                    .build();
            CompletableFuture<HttpResponse<String>> response =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply((r)->r.body()).get(5, TimeUnit.SECONDS);
            return result.equals("deleted")? true : false;
        } catch (Exception e) {
            return false;
        }
    }


}



