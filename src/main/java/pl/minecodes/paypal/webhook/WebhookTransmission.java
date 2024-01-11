package pl.minecodes.paypal.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookTransmission {
    @SerializedName("webhook_url")
    private String webhookUrl;

    @SerializedName("http_status")
    private int httpStatus;

    @SerializedName("reason_phrase")
    private String reasonPhrase;

    @SerializedName("response_headers")
    private Map<String, String> responseHeaders;

    @SerializedName("transmission_id")
    private String transmissionId;

    @SerializedName("status")
    private String status;

    @SerializedName("timestamp")
    private String timestamp;
}