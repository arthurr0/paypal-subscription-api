package pl.minecodes.paypal;

import com.google.gson.Gson;
import java.net.http.HttpClient;
import java.util.logging.Logger;
import lombok.Getter;
import pl.minecodes.paypal.authorization.AuthorizationCredentials;
import pl.minecodes.paypal.authorization.AuthorizationServiceImplementation;
import pl.minecodes.paypal.subscription.SubscriptionExecutor;
import pl.minecodes.paypal.subscription.SubscriptionExecutorImplementation;
import pl.minecodes.paypal.webhook.WebhookServiceImplementation;

@Getter
public class SubscriptionInitializer {

  private final Gson gson;
  public final Logger logger;

  public final HttpClient client;

  private final WebhookServiceImplementation webhookService;
  private final AuthorizationCredentials authorizationCredentials;
  private final AuthorizationServiceImplementation authorizationService;
  private final SubscriptionExecutorImplementation subscriptionExecutor;

  public SubscriptionInitializer(AuthorizationCredentials authorizationCredentials) {
    this.gson = new Gson();
    this.client = HttpClient.newHttpClient();
    this.logger = Logger.getLogger(SubscriptionExecutor.class.getName());

    this.authorizationCredentials = authorizationCredentials;
    this.authorizationService = new AuthorizationServiceImplementation(this);
    this.subscriptionExecutor = new SubscriptionExecutorImplementation(this);

    this.webhookService = new WebhookServiceImplementation(this);
  }
}
