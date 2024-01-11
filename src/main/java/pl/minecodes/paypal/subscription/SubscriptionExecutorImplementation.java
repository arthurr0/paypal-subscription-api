package pl.minecodes.paypal.subscription;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.Data;
import pl.minecodes.paypal.SubscriptionInitializer;
import pl.minecodes.paypal.authorization.AuthorizationCredentials;
import pl.minecodes.paypal.authorization.AuthorizationServiceImplementation;
import pl.minecodes.paypal.exception.AccessTokenException;
import pl.minecodes.paypal.exception.ActivateSubscriptionException;
import pl.minecodes.paypal.exception.CancelSubscriptionException;
import pl.minecodes.paypal.exception.CreateSubscriptionException;
import pl.minecodes.paypal.exception.DetailsSubscriptionException;
import pl.minecodes.paypal.exception.SuspendSubscriptionException;
import pl.minecodes.paypal.subscription.rest.SubscriptionRequest;

@Data
public class SubscriptionExecutorImplementation implements SubscriptionExecutor {
  
  private final Gson gson;
  private final HttpClient client;
  private final AuthorizationServiceImplementation authorizationService;
  private final AuthorizationCredentials authorizationCredentials;

  public SubscriptionExecutorImplementation(SubscriptionInitializer subscriptionInitializer) {
    this.gson = subscriptionInitializer.getGson();
    this.client = subscriptionInitializer.getClient();
    this.authorizationService = subscriptionInitializer.getAuthorizationService();
    this.authorizationCredentials = subscriptionInitializer.getAuthorizationCredentials();
  }

  @Override
  public Subscription createSubscription(SubscriptionRequest request) throws CreateSubscriptionException, AccessTokenException {
    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(this.authorizationCredentials.getApiUrl() + "/v1/billing/subscriptions"))
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .header("Authorization", "Bearer " + this.authorizationService.getAccessToken())
        .POST(BodyPublishers.ofString(this.gson.toJson(request)))
        .build();

    try {
      HttpResponse<String> response = client.send(httpRequest, BodyHandlers.ofString());
      return this.gson.fromJson(response.body(), Subscription.class);
    } catch (Exception exception) {
      throw new CreateSubscriptionException("Cannot create subscription", exception);
    }
  }

  @Override
  public Subscription getSubscriptionDetails(String subscriptionId) throws AccessTokenException, DetailsSubscriptionException {
    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(this.authorizationCredentials.getApiUrl() + "/v1/billing/subscriptions/" + subscriptionId))
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .header("Authorization", "Bearer " + this.authorizationService.getAccessToken())
        .GET()
        .build();

    try {
      HttpResponse<String> response = client.send(httpRequest, BodyHandlers.ofString());
      return this.gson.fromJson(response.body(), Subscription.class);
    } catch (Exception exception) {
      throw new DetailsSubscriptionException("Cannot get subscription details", exception);
    }
  }

  @Override
  public void suspendSubscription(String subscriptionId, String reason) throws SuspendSubscriptionException, AccessTokenException {
    JsonObject body = new JsonObject();
    body.addProperty("reason", reason);

    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(this.authorizationCredentials.getApiUrl() + "/v1/billing/subscriptions/" + subscriptionId + "/suspend"))
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .header("Authorization", "Bearer " + this.authorizationService.getAccessToken())
        .POST(BodyPublishers.ofString(this.gson.toJson(body)))
        .build();

    try {
      this.client.send(httpRequest, BodyHandlers.ofString());
    } catch (Exception exception) {
      throw new SuspendSubscriptionException("Cannot suspend subscription", exception);
    }
  }

  @Override
  public void cancelSubscription(String subscriptionId, String reason) throws CancelSubscriptionException, AccessTokenException {
    JsonObject body = new JsonObject();
    body.addProperty("reason", reason);

    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(this.authorizationCredentials.getApiUrl() + "/v1/billing/subscriptions/" + subscriptionId + "/cancel"))
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .header("Authorization", "Bearer " + this.authorizationService.getAccessToken())
        .POST(BodyPublishers.ofString(this.gson.toJson(body)))
        .build();

    try {
      this.client.send(httpRequest, BodyHandlers.ofString());
    } catch (Exception exception) {
      throw new CancelSubscriptionException("Cannot cancel subscription", exception);
    }
  }

  @Override
  public void activateSubscription(String subscriptionId) throws ActivateSubscriptionException, AccessTokenException {
    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(this.authorizationCredentials.getApiUrl() + "/v1/billing/subscriptions/" + subscriptionId + "/activate"))
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .header("Authorization", "Bearer " + this.authorizationService.getAccessToken())
        .POST(BodyPublishers.noBody())
        .build();

    try {
      this.client.send(httpRequest, BodyHandlers.ofString());
    } catch (Exception exception) {
      throw new ActivateSubscriptionException("Cannot activation subscription", exception);
    }
  }

}
