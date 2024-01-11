package pl.minecodes.paypal.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizationCredentials {

  private final String apiUrl;
  private final String clientId;
  private final String secret;

}
