package com.istasyon.backend.dataObjects;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String employerComment;
    private Double efficiency;
    private Double communication;
    private Double team_work;
    private Double responsibility;
    private Double personal_growth;
}
