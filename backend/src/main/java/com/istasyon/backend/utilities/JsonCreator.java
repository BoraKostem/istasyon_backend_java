package com.istasyon.backend.utilities;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JsonCreator {
    public ResponseEntity<CustomJson<String>> create(String message, int... responseCode) {
        if(responseCode.length == 0) {
            return ResponseEntity.ok(new CustomJson<>(message));
        } else {
            return ResponseEntity.status(responseCode[0]).body(new CustomJson<>(message, responseCode[0]));
        }
    }
}
