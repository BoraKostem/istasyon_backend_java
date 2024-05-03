package com.istasyon.backend.dataObjects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CompanyProfileDTO {
    private MultipartFile profilePhoto = null;
    private String aboutMe = null;
    private String CompanyIndustry = null;
}
