package fakerest.response;

import lombok.Data;

@Data
public class ActivitiesResponse {
    private int id;
    private String title;
    private String dueDate;
    private boolean completed;
}
