package pl.minecodes.paypal.authorization;

import pl.minecodes.paypal.exception.AccessTokenException;

public interface AuthorizationService {

  String getAccessToken() throws AccessTokenException;

}
