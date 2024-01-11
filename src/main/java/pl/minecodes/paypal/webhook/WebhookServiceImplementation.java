package pl.minecodes.paypal.webhook;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import pl.minecodes.paypal.SubscriptionInitializer;
import pl.minecodes.paypal.authorization.AuthorizationCredentials;
import pl.minecodes.paypal.authorization.AuthorizationService;
import pl.minecodes.paypal.exception.AccessTokenException;

public class WebhookServiceImplementation implements WebhookService {

  private final Gson gson;
  private final HttpClient client;
  private final AuthorizationService authorizationService;
  private final AuthorizationCredentials authorizationCredentials;

  public WebhookServiceImplementation(SubscriptionInitializer subscriptionInitializer) {
    this.gson = subscriptionInitializer.getGson();
    this.client = subscriptionInitializer.getClient();
    this.authorizationService = subscriptionInitializer.getAuthorizationService();
    this.authorizationCredentials = subscriptionInitializer.getAuthorizationCredentials();
  }

  @Override
  public WebhookEvent parseWebhookEvent(String json) {
    return this.gson.fromJson(json, WebhookEvent.class);
  }

  @Override
  public boolean validateWebhook(String webhookId, String rawJsonBody, Map<String, String> headers) throws AccessTokenException {
    String certUrl = headers.get("paypal-cert-url");
    String actualSignatureEncoded = headers.get("paypal-transmission-sig");
    String authAlgo = headers.get("paypal-auth-algo");
    String transmissionId = headers.get("paypal-transmission-id");
    String transmissionTime = headers.get("paypal-transmission-time");

    JsonObject payload = new JsonObject();
    payload.addProperty("auth_algo", authAlgo);
    payload.addProperty("cert_url", certUrl);
    payload.addProperty("transmission_id", transmissionId);
    payload.addProperty("transmission_sig", actualSignatureEncoded);
    payload.addProperty("transmission_time", transmissionTime);
    payload.addProperty("webhook_id", webhookId);
    payload.add("webhook_event", JsonParser.parseString(rawJsonBody));

    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(this.authorizationCredentials.getApiUrl() + "/v1/notifications/verify-webhook-signature"))
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + this.authorizationService.getAccessToken())
        .POST(BodyPublishers.ofString(payload.toString()))
        .build();

    try {
      String response = client.send(httpRequest, BodyHandlers.ofString()).body();
      return JsonParser.parseString(response).getAsJsonObject().get("verification_status").getAsString().equals("SUCCESS");
    } catch (Exception exception) {
      return false;
    }
  }
}
