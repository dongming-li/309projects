package sa_b_2.coms309.dungeonadventure.ui.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import sa_b_2.coms309.dungeonadventure.R;
import sa_b_2.coms309.dungeonadventure.game.Constants;
import sa_b_2.coms309.dungeonadventure.network.HttpParse;
import sa_b_2.coms309.dungeonadventure.ui.ScreenObjects.ScreenMessage;

/**
 * An Add Friend screen
 * 99% of this was generated by android studio
 */
public class AddFriendActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    @Nullable
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mUsernameView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        // Set up the login form.
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

        mUsernameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.add_friend || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mUsernameSignInButton = (Button) findViewById(R.id.add_friend_button);
        mUsernameSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsernameView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(username);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isUsernameValid(@NonNull String username) {
        return (username.length()) <= 20 && (username.length() >= 6) && !username.contains(" ") && !username.contains(",");
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private int response = -1;

        UserLoginTask(String username) {
            mUsername = username;
        }

        @NonNull
        @Override
        protected Boolean doInBackground(Void... params) {
            HashMap<String, String> map = new HashMap<>();
            map.put("to", mUsername);
            map.put("from", Constants.currentUser.getUsername());

            String loginURL = "http://proj-309-sa-b-2.cs.iastate.edu/AddFriend.php";
            String finalResult = HttpParse.postRequest(map, loginURL);

            if (finalResult.equals("Something Went Wrong")) {
                response = 3;
                return false;
            }
            if (finalResult.equals("Not connected to the internet"))
                return false;

            response = Integer.parseInt(finalResult);

            return response == 0;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Constants.screenMessages.add(new ScreenMessage("Request sent to " + mUsername));
                finish();
            } else
                switch (response) {
                    case -1:
                        mUsernameView.setError(getString(R.string.error_internet_connection));
                        mUsernameView.requestFocus();
                        break;
                    case 0:
                        mUsernameView.setError(getString(R.string.error_bad_code));
                        mUsernameView.requestFocus();
                        break;
                    case 1:
                        mUsernameView.setError(getString(R.string.error_already_sent));
                        mUsernameView.requestFocus();
                        break;
                    case 2:
                    case 3:
                        mUsernameView.setError(getString(R.string.error_server_error));
                        mUsernameView.requestFocus();
                        break;
                    case 4:
                        mUsernameView.setError(getString(R.string.error_add_self));
                        mUsernameView.requestFocus();
                        break;
                }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

