Feature: Conversion from degree to fahrenheit with the converter "Degree to Fahrenheit"

  Scenario: Converting a temperature in Degree to a temperature in Fahrenheit
    Given my converter exists
    When I call my converter
    Then it should correctly convert the temperature

  Scenario: Trying to convert a temperature in Kelvin to a temperature in Fahrenheit
    Given my converter exists
    When I call my converter on a temperature in Kelvin
    Then it should raise an IllegalArgumentException