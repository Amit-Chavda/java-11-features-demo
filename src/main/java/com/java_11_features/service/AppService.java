package com.java_11_features.service;

import com.java_11_features.dto.Cycle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class AppService {

    public String fileMethods(String data) throws IOException {

        // Get root file path
        Path path = Path.of(System.getProperty("user.dir"));

        // Create file with given data
        Path filePath = Files.writeString(Files.createTempFile(path, "demo", ".txt"), data);

        String content = Files.readString(filePath);
        log.info("File Content: " + content);

        return "Data written in file successfully";
    }

    public String stringMethods(String str, int reps) {

        // String methods
        log.info("---------String Methods-------------");
        log.info("Is blank? " + str.isBlank());
        log.info("Lines: " + str.lines().collect(Collectors.toList()));
        log.info("Repeat " + reps + ": " + str.repeat(reps));
        log.info("Strip Leading: " + str.stripLeading());
        log.info("Strip Trailing: " + str.stripTrailing());
        log.info("Strip :" + str.strip());

        log.info("---------Not Predicate-------------");
        Stream<String> lines = str.lines();
        List<String> list = lines.filter(Predicate.not(String::isBlank)).map(String::strip).collect(Collectors.toList());
        log.info("Not Predicate: " + list);

        log.info("---------toArray() Method-------------");
        String[] array = list.toArray(String[]::new);
        for (String a : array) {
            log.info(a);
        }
        return "Please check console logs";
    }

    public List<String> varExample(List<String> sampleList) {
        return sampleList.stream()
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isBlank))
                .map((var x) -> x.substring(0, 3).toUpperCase())
                .collect(Collectors.toList());
    }

    public String httpClient(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url)).build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return httpResponse.body();
    }

    public String nestBasedAccess() {

        //top level class is nest host
        log.info("Nest host of NanoCycle class: " + Cycle.NanoCycle.class.getNestHost().getName());
        log.info("NanoCycle is nest-mate of Cycle: " + Cycle.class.isNestmateOf(Cycle.NanoCycle.class));

        Set<String> nestMembers = Arrays.stream(Cycle.class.getNestMembers()).map(Class::getName).collect(Collectors.toSet());
        log.info("Nest members of Cycle class: " + nestMembers);

        Cycle.NanoCycle nanoCycle = new Cycle.NanoCycle();
        nanoCycle.testMethod();
        return "Please check console logs";
    }
}
