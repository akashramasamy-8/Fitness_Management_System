package com.project.manage.Dto;

public class WorkoutPlanDTO {
    private String trainer;
    private String title;
    private String desc;

    public WorkoutPlanDTO(String trainer, String title, String desc) {
        this.trainer = trainer;
        this.title = title;
        this.desc = desc;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
