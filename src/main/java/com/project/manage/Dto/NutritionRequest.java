package com.project.manage.Dto;

public class NutritionRequest {
    private String ingredient;
    private Long userId;

    public NutritionRequest(String ingredient, Long userId) {
        this.ingredient = ingredient;
        this.userId = userId;
    }

    public String getIngredient() {

        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
