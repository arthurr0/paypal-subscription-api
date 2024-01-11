package pl.minecodes.paypal.exception;

public class CancelSubscriptionException extends Exception {

  public CancelSubscriptionException(String message) {
    super(message);
  }

  public CancelSubscriptionException(String message, Throwable cause) {
    super(message, cause);
  }

}
