Feature: CarWale

Scenario: Lowest Km Car

Given Launch https://www.carwale.com/
And Click on Used
And Select the City as Chennai
And Select budget min 8L and max 12L and Click Search
And Select Cars with Photos under Only Show Cars With
And Select Manufacturer as Hyundai --> Creta
And Select Fuel Type as Petrol
When Select Best Match as 'KM: Low to High'
Then Print the first resulting car