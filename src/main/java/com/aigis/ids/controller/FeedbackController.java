package com.aigis.ids.controller;


import com.aigis.ids.configuration.ClientConfig;
import com.aigis.ids.dto.Feedback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
@Slf4j
@RequiredArgsConstructor
public class FeedbackController {

    private final ClientConfig mlconfig;

    @PostMapping
    public ResponseEntity<String> giveFeedback(
            @RequestBody Feedback feedback) {

        String response = mlconfig.mlClient()
                .post()
                .uri("/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .body(feedback)
                .retrieve()
                .body(String.class);

        return ResponseEntity.ok(response);
    }
}
