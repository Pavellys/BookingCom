Feature: Search on Booking.com

  Scenario: Search by city criteria
    Given User is looking for 'Минск' city
    When User does search
    Then Hotel 'DoubleTree by Hilton Минск' should be on the first page

  Scenario: Search by city criteria
    Given User is looking for 'Брест' city
    When User does search
    Then Hotel 'Hampton by Hilton Brest' should be on the first page