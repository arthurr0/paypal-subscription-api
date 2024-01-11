package pl.minecodes.paypal.subscription;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionSubscriber {

    private SubscriptionName name;
    @SerializedName("email_address")
    private String emailAddress;
    @SerializedName("payer_id")
    private String payerId;
    @SerializedName("shipping_address")
    private SubscriptionShippingAddress shippingAddress;

}
