# API Testing with Fixer API

This repository contains automated tests for the [Fixer API](https://apilayer.com/marketplace/fixer-api). The goal is to
test various situation for one endpoint of the API. The project is built using Java, Maven, and Cucumber
for BDD (Behavior Driven Development) testing.

### Scope of Testing

The primary focus of testing is on the `latest` endpoint of the Fixer API. The following tests have been covered:

1. **200 - Success**: Verify that the `latest` endpoint returns the correct exchange rates for different currencies.
example:
```json
   {
   "success": true,
   "timestamp": 1728219456,
   "base": "EUR",
   "date": "2024-10-06",
   "rates": {
   "USD": 1.098172
   }
```   
3. **200 - Bad Request**: Verify that the API returns a `200 Bad Request` when invalid parameters are passed.
example:
```json   
  {
   "success": false,
   "error": {
   "code": 202,
   "type": "invalid_currency_codes",
   "info": "You have provided one or more invalid Currency Codes. [Required format: currencies=EUR,USD,GBP,...]"
   }
```
5. **401 - Unauthorized**: Test the response when an API key is missing or invalid.
example:
```json
  {
   "message":"No API key found in request"
  }
```
### Tools Used

- **Java 21**
- **Maven**
- **Cucumber**
- **JUnit**




