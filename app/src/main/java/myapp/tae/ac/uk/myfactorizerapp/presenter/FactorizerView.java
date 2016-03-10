package myapp.tae.ac.uk.myfactorizerapp.presenter;

import android.app.ProgressDialog;

/**
 * Created by Karma on 10/03/16.
 */
public interface FactorizerView {
    String getInputEntry();

    void showInvalidEntryError(int resId);

    void showEmptyEntryError(int resId);

    void showCancelledToast(int resId);

    ProgressDialog getProgressDialog();

    void updateResult(String string);
}
