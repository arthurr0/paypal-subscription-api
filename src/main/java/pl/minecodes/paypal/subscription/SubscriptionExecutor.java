package pl.minecodes.paypal.subscription;


import pl.minecodes.paypal.exception.AccessTokenException;
import pl.minecodes.paypal.exception.ActivateSubscriptionException;
import pl.minecodes.paypal.exception.CancelSubscriptionException;
import pl.minecodes.paypal.exception.CreateSubscriptionException;
import pl.minecodes.paypal.exception.DetailsSubscriptionException;
import pl.minecodes.paypal.exception.SuspendSubscriptionException;
import pl.minecodes.paypal.subscription.rest.SubscriptionRequest;

public interface SubscriptionExecutor {

  Subscription createSubscription(SubscriptionRequest request)
      throws CreateSubscriptionException, AccessTokenException;

  Subscription getSubscriptionDetails(String subscriptionId)
      throws AccessTokenException, DetailsSubscriptionException;

  void suspendSubscription(String subscriptionId, String reason)
      throws SuspendSubscriptionException, AccessTokenException;

  void cancelSubscription(String subscriptionId, String reason)
      throws CancelSubscriptionException, AccessTokenException;

  void activateSubscription(String subscriptionId)
      throws ActivateSubscriptionException, AccessTokenException;
}
