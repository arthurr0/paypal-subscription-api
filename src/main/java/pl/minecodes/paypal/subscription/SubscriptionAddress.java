package pl.minecodes.paypal.subscription;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionAddress {

    @SerializedName("address_line_1")
    private String addressLine1;
    @SerializedName("address_line_2")
    private String addressLine2;
    @SerializedName("admin_area_2")
    private String adminArea2;
    @SerializedName("admin_area_1")
    private String adminArea1;
    @SerializedName("postal_code")
    private String postalCode;
    @SerializedName("country_code")
    private String countryCode;

}
