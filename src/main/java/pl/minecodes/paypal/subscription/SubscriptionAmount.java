package pl.minecodes.paypal.subscription;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionAmount {

    @SerializedName("currency_code")
    private String currencyCode;
    private String value;

}
