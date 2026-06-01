package com.afyke.demo.controller;

import com.afyke.demo.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping
    public ApiResponse<Map<String, Object>> getTest(
            @RequestParam(defaultValue = "axios") String name
    ) {
        return ApiResponse.success("GET request success", Map.of(
                "name", name,
                "method", "GET",
                "serverTime", now()
        ));
    }

    @PostMapping
    public ApiResponse<Map<String, Object>> postTest(
            @RequestBody(required = false) TestRequest request
    ) {
        String username = request == null || request.username() == null
                ? "anonymous"
                : request.username();
        String content = request == null || request.content() == null
                ? ""
                : request.content();

        return ApiResponse.success("POST request success", Map.of(
                "username", username,
                "content", content,
                "method", "POST",
                "serverTime", now()
        ));
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public record TestRequest(String username, String content) {
    }
}
