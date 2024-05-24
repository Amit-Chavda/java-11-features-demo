package com.java_11_features.controller;

import com.java_11_features.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @PostMapping("/file")
    public ResponseEntity<String> writeFile(@RequestBody String data) throws IOException {
        return new ResponseEntity<>(appService.fileMethods(data), HttpStatus.OK);
    }

    @PostMapping("/str")
    public ResponseEntity<String> applyStringMethods(@RequestBody String str, @RequestParam int reps) {
        return new ResponseEntity<>(appService.stringMethods(str, reps), HttpStatus.OK);
    }

    @PostMapping("/var")
    public ResponseEntity<List<String>> substringAndFilter(@RequestBody List<String> list) {
        return new ResponseEntity<>(appService.varExample(list), HttpStatus.OK);
    }

    @GetMapping("/httpClient")
    public ResponseEntity<Object> getHttp(@RequestParam String url) throws IOException, InterruptedException {
        return new ResponseEntity<>(appService.httpClient(url), HttpStatus.OK);
    }

    @GetMapping("/nba")
    public ResponseEntity<String> nestBasedControlAccess() {
        return new ResponseEntity<>(appService.nestBasedAccess(), HttpStatus.OK);
    }
}
