# orientation-mode
This source is about saving instance of application during orientation change.

Most of Android Developers put android:screenOrientation="portrait" in their apps and force users using apps only in portrait mode.
Because, when orientation changes, Activities relaunches, and all of operations restart again.
This source will help you save instances of activity or fragment and get saved data without restarting. 
And also, without forcing activity with this code in manifest file:
"activity name= ".YourActivity" android:configChanges="orientation|screenSize" "

This is testing for new Branch! 
