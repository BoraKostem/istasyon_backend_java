package com.istasyon.backend.controllers;

import com.istasyon.backend.dataObjects.requests.VerifyMailRequest;
import com.istasyon.backend.repositories.UserRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/verify")
public class VerificationController {

    private UserRepo userRepo;
    private JsonCreator jsonCreator;

    public VerificationController(UserRepo userRepo, JsonCreator jsonCreator){
        this.userRepo=userRepo;
        this.jsonCreator=jsonCreator;
    }
    @PostMapping("/email")
    public ResponseEntity<CustomJson<Object>> verify(@RequestBody VerifyMailRequest mailRequest) {

        var verifyResult=this.VerficationProviderResult(mailRequest.verificationCode);

        if(!verifyResult){
            return jsonCreator.create(false);
        }

        var verifyingUser=userRepo.findById(Long.valueOf(mailRequest.userId)).get();

        verifyingUser.setIsVerified(verifyResult);

        userRepo.saveAndFlush(verifyingUser);

        return jsonCreator.create(true);
        //return "dashboard page";
    }

    @GetMapping("/requestVerificationCode")
    public ResponseEntity<CustomJson<Object>>  requestCode(){
        //send code to email with provider

        return jsonCreator.create(true);
    }

    private boolean VerficationProviderResult(Number verifyCode){
        //send request and return tru if verified
        return false;
    }
}
