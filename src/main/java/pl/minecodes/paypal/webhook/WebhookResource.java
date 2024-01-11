package pl.minecodes.paypal.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookResource {
    @SerializedName("start_time")
    private String startTime;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("subscriber")
    private WebhookSubscriber subscriber;

    @SerializedName("create_time")
    private String createTime;

    @SerializedName("links")
    private List<WebhookLink> links;

    @SerializedName("id")
    private String id;

    @SerializedName("plan_overridden")
    private boolean planOverridden;

    @SerializedName("plan_id")
    private String planId;

    @SerializedName("status")
    private String status;
}