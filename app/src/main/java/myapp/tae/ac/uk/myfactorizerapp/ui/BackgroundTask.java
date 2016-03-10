package myapp.tae.ac.uk.myfactorizerapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Karma on 10/03/16.
 */
public class BackgroundTask extends AsyncTask<Double, Void, ArrayList<Double>> implements DialogInterface.OnCancelListener {
    private ProgressDialog progressDialog;
    private boolean isBackgroundinProgress;

    public BackgroundTask(ProgressDialog progressDialog) {
        super();
        this.progressDialog = progressDialog;
        isBackgroundinProgress = false;
    }

    @Override
    protected void onPreExecute() {
        isBackgroundinProgress = true;
    }

    @Override
    protected ArrayList<Double> doInBackground(Double... params) {
        ArrayList<Double> factors = new ArrayList<>();
        for (double i = 0; i <= params[0]; i++) {
            if ((params[0] % i) == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

    @Override
    protected void onPostExecute(ArrayList<Double> factors) {
        isBackgroundinProgress = false;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        this.cancel(true);
    }

    public boolean isBackgroundInProgress() {
        return isBackgroundinProgress;
    }
}
