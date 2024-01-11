package pl.minecodes.paypal.subscription;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionPaymentMethod {

    @SerializedName("payer_selected")
    private String payerSelected;
    @SerializedName("payee_preferred")
    private String payeePreferred;

}