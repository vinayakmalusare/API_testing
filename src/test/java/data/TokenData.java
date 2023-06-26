package data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenData {

    private String username;
    private String password;
}
