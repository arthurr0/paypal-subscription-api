package pl.minecodes.paypal.subscription;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionApplicationContext {

    @SerializedName("brand_name")
    private String brandName;
    private String locale;
    @SerializedName("shipping_preference")
    private String shippingPreference;
    @SerializedName("user_action")
    private String userAction;
    @SerializedName("payment_method")
    private SubscriptionPaymentMethod paymentMethod;
    @SerializedName("return_url")
    private String returnUrl;
    @SerializedName("cancel_url")
    private String cancelUrl;

}