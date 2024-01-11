package pl.minecodes.paypal.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.google.gson.annotations.SerializedName;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookSubscriber {
    @SerializedName("email_address")
    private String emailAddress;

    @SerializedName("name")
    private Name name;

    @Data
    public static class Name {
        @SerializedName("given_name")
        private String givenName;

        @SerializedName("surname")
        private String surname;
    }
}