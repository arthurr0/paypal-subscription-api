package pl.minecodes.paypal.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionLink {

    private String href;
    private String rel;
    private String method;

}
