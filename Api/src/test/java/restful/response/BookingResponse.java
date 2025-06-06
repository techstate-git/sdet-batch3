package restful.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {
    @JsonProperty("bookingid")
    private Integer bookingid;
    private BookingDetails booking;

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BookingDetails {
        private String firstname;
        private String lastname;
        private Integer totalprice;
        private Boolean depositpaid;
        private BookingDates bookingdates;
        private String additionalneeds;
    }
}
