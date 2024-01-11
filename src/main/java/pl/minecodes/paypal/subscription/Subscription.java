package pl.minecodes.paypal.subscription;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

  private String id;
  private SubscriptionStatus status;
  @SerializedName("status_update_time")
  private String statusUpdateTime;
  @SerializedName("plan_id")
  private String planId;
  @SerializedName("plan_overridden")
  private boolean planOverridden;
  @SerializedName("start_time")
  private String startTime;
  private String quantity;
  @SerializedName("shipping_amount")
  private SubscriptionAmount shippingAmount;
  private SubscriptionSubscriber subscriber;
  @SerializedName("create_time")
  private String createTime;
  private List<SubscriptionLink> links;

  public String getApproveUrl(){
    return this.links.stream()
        .filter(subscriptionLink -> subscriptionLink.getRel().equals("approve"))
        .map(SubscriptionLink::getHref)
        .findFirst()
        .orElse(null);
  }
}
