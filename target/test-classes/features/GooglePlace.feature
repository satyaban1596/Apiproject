Feature: Google place functionality

Scenario Outline:: Create new place on the google map

Given user prepare a reguest with payload "<Name>" "<Pnumber>" "<Address>"
When user send the "POST" request
And user get status code of "200"
Then validate "status" value is "OK"
Then validate "scope" value is "APP"
And Validate Place_id Created maps is "<Name>"

Examples:
|Name|Pnumber|Address|
|Jude|43562736|England|
|vini|4363920|Brazil|