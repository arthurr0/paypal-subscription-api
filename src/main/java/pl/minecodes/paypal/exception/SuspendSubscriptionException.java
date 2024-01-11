package pl.minecodes.paypal.exception;

public class SuspendSubscriptionException extends Exception {

  public SuspendSubscriptionException(String message) {
    super(message);
  }

  public SuspendSubscriptionException(String message, Throwable cause) {
    super(message, cause);
  }

}
