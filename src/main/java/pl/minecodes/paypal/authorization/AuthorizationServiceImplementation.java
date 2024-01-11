package pl.minecodes.paypal.authorization;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;
import pl.minecodes.paypal.SubscriptionInitializer;
import pl.minecodes.paypal.exception.AccessTokenException;

public class AuthorizationServiceImplementation implements AuthorizationService {

  private final Gson gson;
  private final HttpClient client;
  private final AuthorizationCredentials authorizationCredentials;

  public AuthorizationServiceImplementation(SubscriptionInitializer subscriptionInitializer) {
    this.gson = subscriptionInitializer.getGson();
    this.client = subscriptionInitializer.getClient();
    this.authorizationCredentials = subscriptionInitializer.getAuthorizationCredentials();
  }

  @Override
  public String getAccessToken() throws AccessTokenException {
    String encoded = Base64.getEncoder()
        .encodeToString((this.authorizationCredentials.getClientId() + ":" + this.authorizationCredentials.getSecret())
            .getBytes());

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(this.authorizationCredentials.getApiUrl() + "/v1/oauth2/token"))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .header("Accept", "application/json")
        .header("Authorization", "Basic " + encoded)
        .POST(BodyPublishers.ofString("grant_type=client_credentials"))
        .build();

    try {
      HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
      JsonObject jsonNode = this.gson.fromJson(response.body(), JsonObject.class);
      return jsonNode.get("access_token").getAsString();
    } catch (Exception exception) {
      throw new AccessTokenException("Cannot get access token from PayPal API", exception);
    }
  }
}
