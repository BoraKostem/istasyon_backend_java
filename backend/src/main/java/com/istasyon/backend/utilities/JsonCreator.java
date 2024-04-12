package com.istasyon.backend.utilities;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JsonCreator {

    public ResponseEntity<CustomJson<Object>> create(Object object, int... responseCode) {
        if(responseCode.length == 0) {
            return ResponseEntity.ok(new CustomJson<>(object));
        } else {
            return ResponseEntity.status(responseCode[0]).body(new CustomJson<>(object, responseCode[0]));
        }
    }
    public ResponseEntity<CustomJson<Object>> createLogin(Object object,String cookie, int... responseCode) {
        if(responseCode.length == 0) {
            return ResponseEntity.ok().header("Set-Cookie",cookie).body(new CustomJson<>(object));
        } else {
            return ResponseEntity.status(responseCode[0]).body(new CustomJson<>(object, responseCode[0]));
        }
    }
}
