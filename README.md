## Simple PayPal Subscription Java API

## Maven

---

###### Repository
```xml
<repository>
  <id>minecodes-repository-releases</id>
  <name>mineCodes Organization Repository</name>
  <url>https://repository.minecodes.pl/releases</url>
</repository>
```

###### Repository
```xml
<dependency>
  <groupId>pl.minecodes</groupId>
  <artifactId>paypal-api</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Usage

---

#### Initialize
```java
SubscriptionInitializer subscriptionInitializer = new SubscriptionInitializer(
    new AuthorizationCredentials(
      "API_URL like https://api.sandbox.paypal.com",
      "CLIENT_ID",
      "CLIENT_SECRET"
    )
);
```
---

#### Subscription creation
```java
Subscription subscription = this.subscriptionExecutor.createSubscription(new SubscriptionRequest(
    "PLAN ID",
    "START TIME",
    1, // Quantity
    1, // Shipping amount
    new SubscriptionSubscriber(
        new SubscriptionName(
            user.getFirstName(),
            user.getLastName()
        ),
        user.getEmail(),
        user.getEmail(),
        null
    ), // Client data
    new SubscriptionApplicationContext(
        "Example.com",
        "en-US",
        "NO_SHIPPING",
        "SUBSCRIBE_NOW",
        new SubscriptionPaymentMethod(
            "PAYPAL",
            "IMMEDIATE_PAYMENT_REQUIRED"
        ),
        "https://example.com/payments?status=success",
        "https://example.com/payments?status=cancel"
    ) // Application context
));
```
---

#### Webhook validation

###### SpringBoot example

```java
  @PostMapping(path = "/billing-subscription-created", consumes = "application/json")
  public ResponseEntity<?> receiveCreatedWebhook(
      @RequestBody String rawJson,
      @RequestHeader Map<String, String> headers
  ) throws AccessTokenException {
    String webhookId = "EXAMPLE_WEBHOOK_ID";

    WebhookEvent webhookEvent = this.webhookService.parseWebhookEvent(rawJson);

    boolean validatedWebhook = this.webhookService.validateWebhook(webhookId, rawJson, headers);
    
    return ResponseEntity.ok("Webhook Validated: " + true);
  }
```

