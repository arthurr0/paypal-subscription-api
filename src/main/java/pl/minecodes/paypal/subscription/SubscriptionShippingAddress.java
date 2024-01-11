package pl.minecodes.paypal.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionShippingAddress {

    private SubscriptionName name;
    private SubscriptionAddress address;

}
