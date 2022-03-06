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

        //criando a requisi��o
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of(FILE_Json)))    /* se comentarmos/removermos esta linha,
                por default, o m�todo GET � usado e n�o retorna "405 Method Not Allowed", mas sim, a p�gina html*/
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(URL_POST))
                .build();

        // enviando uma solicita��o (ass�ncrona)
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
