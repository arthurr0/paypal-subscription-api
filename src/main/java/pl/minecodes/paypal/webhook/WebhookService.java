package pl.minecodes.paypal.webhook;

import java.util.Map;
import pl.minecodes.paypal.exception.AccessTokenException;

public interface WebhookService {

  WebhookEvent parseWebhookEvent(String json);

  boolean validateWebhook(String webhookId, String rawJsonBody, Map<String, String> headers) throws AccessTokenException;

}
