@Fixer
Feature: GET_latest

    Tests of possible http response codes for GET_latest endpoint.

    Scenario: 200 - Get the latest rates with default values | HAPPY PATH
        Given I have a valid API key as a user
        When I call GET_latest endpoint: /latest
        Then I receive 200 code
            And default schema response body matches model from documentation

    Scenario: 200 - Get the latest rate for EUR-GBP | HAPPY PATH
        Given I have a valid API key as a user
        When I call GET_latest endpoint /latest with query (nulls ignored):
            | base | symbols |
            | EUR  | GBP     |
        Then I receive 200 code
            And default schema response body matches model from documentation

    Scenario: 200 - Invalid query parameters for GET /latest | SAD PATH
        Given I have a valid API key as a user
        When I call GET_latest endpoint /latest with query with non existent base param (nulls ignored):
            | base | symbols |
            | AAA  | GBP     |
        Then I receive 200 code
            And error schema response body matches model from documentation

    Scenario: 401 - Calling Get /latest with no access token in header | SAD PATH
        When I call GET_latest endpoint /latest with no access token in header
        Then I receive 401 code
            And no token error schema response body matches model from documentation
