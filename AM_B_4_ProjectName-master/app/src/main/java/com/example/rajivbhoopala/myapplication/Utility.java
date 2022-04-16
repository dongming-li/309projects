package com.example.rajivbhoopala.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Joel on 10/8/2017.
 */

public class Utility {

    private Context context;
    private Toast toast;


    public Utility(Context context) {
        this.context = context;
    }

    /**
     * Launches an Activity
     * @param context takes a context.
     * @param activity activity needed to launch.
     *
     * Usage:
     * From activity: new Utility(this).launchActivity(this, Activity.class);
     * From fragment: new Utility(getActivity()).launchActivity(getActivity(), Activity.class);
     */
    public void launchActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(this.context, activity);
        context.startActivity(intent);
    }

    /**
     * Displays a short toast
     * @param text short string to be displayed.
     *
     * Usage:
     * From activity: new Utility(this).displayShortToast("Text");
     * From fragment: new Utility(getActivity()).displayShortToast("Text");
     */
    public void displayShortToast(String text) {
        if (toast == null)
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        else
            toast.setText(text);

        toast.show();
    }

    /**
     * Displays a long toast
     * @param text long string to be displayed.
     *
     * Usage:
     * From activity: new Utility(this).displayLongToast("Text");
     * From fragment: new Utility(getActivity()).displayLongToast("Text");
     */
    public void displayLongToast(String text) {
        if (toast == null)
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        else
            toast.setText(text);

        toast.show();
    }

    /**
     * Creates an android dialog builder.
     * @param context takes a context.
     * @param title Dialog title.
     * @param message Message displayed on dialog.
     * @param cancelable Determines if the dialog can be cancelled.
     */
    public AlertDialog.Builder createDialogBuilder(Context context, String title, String message, boolean cancelable)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (title != null)
            builder.setTitle(title);

        if (message != null)
            builder.setMessage(message);

        builder.setCancelable(cancelable);

        return builder;
    }
}
