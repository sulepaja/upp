package com.example.DTO;

public class ReviewDataDTO {

    private String textContent;
    private String recommendation;

    public ReviewDataDTO(){}

    public ReviewDataDTO(String textContent, String recommendation) {
        this.textContent = textContent;
        this.recommendation = recommendation;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
