package nicarng.selcar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;

public class UserSessionManager_Grade {
    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidExamplePref";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_GRADE = "grade";

    // Email address (make variable public to access from outside)
    public static final String KEY_PHONENUMBER = "phonenumber";

    // Constructor
    public UserSessionManager_Grade(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession_GRADE(String grade, String phonenumber){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_GRADE, grade);

        // Storing email in pref
        editor.putString(KEY_PHONENUMBER, phonenumber);

        // commit changes
        editor.commit();
    }


    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn_GRADE()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }

    /**
     * Get stored session data
     * */

    public HashMap<String, String> getUserDetails_GRADE(){
        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_GRADE, pref.getString(KEY_GRADE, null));

        // user email id
        user.put(KEY_PHONENUMBER, pref.getString(KEY_PHONENUMBER, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */

    public void logoutUser(){
        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
     /*   Intent i = new Intent(_context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);*/
    }

    // Check for login
    public boolean isUserLoggedIn_GRADE(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
