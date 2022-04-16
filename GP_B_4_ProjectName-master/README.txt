Have to allow LoginRequest.java and RegisterRequest.java to access the internet.
To do so, add this statement to AndroidManifest.xml:
<uses-permission android:name="android.permission.INTERNET"></uses-permission>

I also used volley in LoginActivity.java and RegisterActivity.java.
To do so, add this statement to the dependencies in build.gradle:
compile 'com.android.volley:volley:1.0.0'
NOTE: after adding the statement, use [BUILD]->[Clean Project].

Login.php and Register.php have to be in the server folder.