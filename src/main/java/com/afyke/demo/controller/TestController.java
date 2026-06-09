package com.afyke.demo.controller;

import com.afyke.demo.common.ApiResponse;
import com.afyke.demo.config.SpringContextHolder;
import com.afyke.demo.service.UserService;
import com.afyke.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userServiceImpl;

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

    @GetMapping("/test")
    public ApiResponse<Map<String, Object>> getSpringMethod(
            @RequestParam(defaultValue = "axios") String name
    ) {
        UserServiceImpl bean = SpringContextHolder.getBean(UserServiceImpl.class);
        bean.doWork();
        return ApiResponse.success("GET request success", Map.of(
                "name", name,
                "method", "GET",
                "serverTime", now()
        ));
    }

    @GetMapping("/testPostProcessAfterInitialization")
    public ApiResponse<String> test() {
        userServiceImpl.doWork();
        return ApiResponse.success("POST request success");
    }


    @PostMapping("/actions")
    public ApiResponse<String> actions(@RequestBody TestRequest testRequest) {
        userServiceImpl.doWork();
        return ApiResponse.success(testRequest.username()+"Actions");
    }


    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public record TestRequest(String username, String content) {
    }
}
