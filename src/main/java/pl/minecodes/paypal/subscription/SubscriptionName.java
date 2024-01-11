package pl.minecodes.paypal.subscription;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionName {

    @SerializedName("given_name")
    private String givenName;
    private String surname;

}
