package pl.minecodes.paypal.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookEvent {
    @SerializedName("id")
    private String id;

    @SerializedName("create_time")
    private String createTime;

    @SerializedName("resource_type")
    private String resourceType;

    @SerializedName("event_type")
    private String eventType;

    @SerializedName("summary")
    private String summary;

    @SerializedName("resource")
    private WebhookResource resource;

    @SerializedName("status")
    private String status;

    @SerializedName("transmissions")
    private List<WebhookTransmission> transmissions;

    @SerializedName("links")
    private List<WebhookLink> links;

    @SerializedName("event_version")
    private String eventVersion;

    @SerializedName("resource_version")
    private String resourceVersion;
}