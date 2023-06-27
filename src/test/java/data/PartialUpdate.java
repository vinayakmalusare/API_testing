package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartialUpdate {
    private String firstname;
    private String lastname;
}
