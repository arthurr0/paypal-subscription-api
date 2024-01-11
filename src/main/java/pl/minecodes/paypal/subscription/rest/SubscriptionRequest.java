package pl.minecodes.paypal.subscription.rest;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.minecodes.paypal.subscription.SubscriptionAmount;
import pl.minecodes.paypal.subscription.SubscriptionApplicationContext;
import pl.minecodes.paypal.subscription.SubscriptionSubscriber;

@Data
@AllArgsConstructor
public class SubscriptionRequest {

  @SerializedName("plan_id")
  private String planId;
  @SerializedName("start_time")
  private String startTime;
  private int quantity;
  @SerializedName("shipping_amount")
  private SubscriptionAmount shippingAmount;
  private SubscriptionSubscriber subscriber;
  @SerializedName("application_context")
  private SubscriptionApplicationContext applicationContext;

}
