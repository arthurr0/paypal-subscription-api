package pl.minecodes.paypal.exception;

import lombok.Getter;

@Getter
public class DetailsSubscriptionException extends Exception {

  public DetailsSubscriptionException(String message) {
    super(message);
  }

  public DetailsSubscriptionException(String message, Throwable cause) {
    super(message, cause);
  }

}
