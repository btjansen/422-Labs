README:
The root application homepage can be reached through the tomcat manager app named /lab2_btjansen. It can also be reached at https://localhost[:port]/lab2_btjansen/

NOTES:
Occasionally an unresolvable reference error would occur when compiling my project. To clear this error, go to the project properties>Java Build Path>Source>Lab2/src and select the Ignore Optional compile problems setting and change it to YES. 
I could not find the root issue of this error but the Ignore Optional compile problems setting allows the project to compile and run correctly

After a successful Add/Delete/Edit of the user list, user is redirected back to Page 1. In order to see the updated user list and user data the page must be refreshed.

Features not implemented:
I was unable to implement the Best 3 matches feature for Page 1
I was unable to implement the input validation for Pages 2-5

ORGANIZATION:
Since all my .java files exist in the same directory I will organize them by MVC below
MODELS:
UserEntry.java
UserList.java
UserCModel.java

VIEWS:
UserCListView.java
UserCDeleteView.java
Page2View.java
Page3View.java
Page4View.java
Page5View.java
Page6View.java
ErrorView.java
Error2View.java

CONTROLLERS:
Controller.java
UserController.java
WelcomeServlet.java
IndexServlet.java