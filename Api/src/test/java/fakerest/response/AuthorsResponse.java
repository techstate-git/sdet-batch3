package fakerest.response;

import lombok.Data;

@Data
public class AuthorsResponse {
    private int id;
    private int idBook;
    private String firstName;
    private String lastName;
}
