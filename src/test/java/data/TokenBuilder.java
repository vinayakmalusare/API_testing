package data;

public class TokenBuilder {

    public static TokenData getToken(){
        return
                TokenData.builder().username("admin").password("password123").build();
    }

}
