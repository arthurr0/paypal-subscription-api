package pl.minecodes.paypal.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.google.gson.annotations.SerializedName;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookLink {
    @SerializedName("href")
    private String href;

    @SerializedName("rel")
    private String rel;

    @SerializedName("method")
    private String method;
}