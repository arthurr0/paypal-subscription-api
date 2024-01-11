package pl.minecodes.paypal.exception;

import lombok.Getter;

@Getter
public class CreateSubscriptionException extends Exception {

  public CreateSubscriptionException(String message) {
    super(message);
  }

  public CreateSubscriptionException(String message, Throwable cause) {
    super(message, cause);
  }

}
