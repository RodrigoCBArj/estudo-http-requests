package com.httpexemples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;

public class postRequest {

    public static final String URL_POST = "https://httpbin.org/forms/post";
    public static final String FILE_Json = "/home/rcba/Estudo/DIO/estudo-api-rest/request.json";

    public static void main(String[] args) throws IOException, InterruptedException {

        // clinete HTTP
        HttpClient client = HttpClient.newHttpClient();

        //criando a requisição
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of(FILE_Json)))    /* se comentarmos/removermos esta linha,
                por default, o método GET é usado e não retorna "405 Method Not Allowed", mas sim, a página html*/
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(URL_POST))
                .build();

        // enviando uma solicitação (assíncrona)
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
