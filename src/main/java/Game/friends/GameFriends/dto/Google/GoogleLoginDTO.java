package Game.friends.GameFriends.dto.Google;

import lombok.Data;

@Data
public class GoogleLoginDTO {
    private String idToken;

    public String getIdToken() {
        return idToken;
    }
}