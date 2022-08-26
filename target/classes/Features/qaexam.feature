Feature: Login to HnH app and create multiple users

#without Examples Keyword
@Login
Scenario:  HnH app Login Test Scenario
Given user is already on Login Page
When title of login page is HnH app
Then user enters "deepti.jindal@hnhconsulting.ca" and "candidate123"
Then user clicks on login button
Then user is on Qa exam page


@CreateUser
Scenario Outline: HnH app Create a new user scenario
Then user enters details "<name>" and "<email>" and "<role>" and click on add user button and check user got added

Examples:
 | name  | email              | role |
 | Peter 	 | tompeter@test.in      | Manager  |
 | David | Dsouza@test.in     | Director |	
	
	
	@Logout
Scenario:  HnH app Logout Test Scenario
When user logout
Then user is on Login page
Then close the browser