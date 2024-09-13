package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class OO9_HttpTest {

    @Test
    void http () throws Exception {

        // ① We want to close the HttpClient automatically. Note that if you do launch any threads
        // and send HTTP requests in them, you should not use auto-closeable unless care is taken only
        // to let it reach the end of the scope after all the threads have finished executing.
        try (var http = HttpClient
                .newHttpClient()){
            var request = HttpRequest.newBuilder(URI.create("https://httpbin.org"))
                    .GET()
                    .build() ;

            // <b> HttpResponse.BodyHandlers </b>
            var response = http.send( request, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals( response.statusCode() , 200);
            System.out.println(response.body());
        }
    }

}