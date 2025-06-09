package stepDefinitions.fakerestSteps;

import fakerest.response.AuthorsResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;

import static fakerest.request.AuthorsRequest.getAuthors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorsSteps {
    Response response;

    @Given("the user is get all authors")
    public void the_user_is_get_all_authors() {
        response = getAuthors();
        response.prettyPrint();
    }

    @Then("status code is {int}")
    public void status_code_is(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Given("the user is get authors by book id {int}")
    public void the_user_is_get_authors_by_book_id(int id) {
        response = getAuthors(id);
        response.prettyPrint();
    }
    @Then("print names of all authors")
    public void print_names_of_all_authors() {
        List<AuthorsResponse> authorsResponseList = response.as(new TypeRef<>() {});

        for (AuthorsResponse author : authorsResponseList) {
            System.out.println(author);
        }
    }
}













