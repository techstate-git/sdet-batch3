package stepDefinitions.fakerestSteps;

import fakerest.response.ActivitiesResponse;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static fakerest.request.ActivitiesRequest.createActivity;
import static fakerest.request.ActivitiesRequest.getActivities;

public class ActivitiesSteps {
    ActivitiesResponse activitiesResponse = new ActivitiesResponse();

    @Given("the user is get all activities")
    public void the_user_is_get_all_activities() {
        Response response = getActivities();
        response.prettyPrint();

        Assert.assertEquals(200, response.getStatusCode());

        List<ActivitiesResponse> activitiesResponseList = response.as(new TypeRef<>() {});

        for (ActivitiesResponse activity : activitiesResponseList) {
            System.out.println(activity.getId());
        }
    }

    @Given("the user is get activity by id = {int}")
    public void the_user_is_get_activity_by_id(int id) {
        Response response = getActivities(id);
        response.prettyPrint();

        ActivitiesResponse activitiesResponse = response.as(new TypeRef<>() {});
        System.out.println(activitiesResponse.getTitle());
    }

    @Given("the user is created activity with id = {int}")
    public void the_user_is_created_activity_with_id(int id) {
        activitiesResponse.setId(id);
    }

    @Given("the title is = {string}")
    public void the_title_is(String title) {
        activitiesResponse.setTitle(title);
    }

    @Given("the due date is = {int} days")
    public void the_due_date_is_days(int days) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
        currentDateTime.format(formatter);
        LocalDateTime dueDateTime = currentDateTime.plus(days, ChronoUnit.DAYS);
        String newDueDateTimeFormatted = dueDateTime.format(formatter);

        activitiesResponse.setDueDate(newDueDateTimeFormatted);
    }

    @Given("activity is completed = {booleanType}")
    public void activity_is_completed_true(boolean isCompleted) {
        activitiesResponse.setCompleted(isCompleted);
    }

    @Then("the user is creating activity using parameters above")
    public void the_user_is_creating_activity_using_parameters_above() {
        Response response = createActivity(activitiesResponse);
        response.prettyPrint();
    }

    @ParameterType("true|false")
    public boolean booleanType(String value) {
        return Boolean.parseBoolean(value);
    }
}

















