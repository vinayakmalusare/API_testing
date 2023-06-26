package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateBookingData {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

}
