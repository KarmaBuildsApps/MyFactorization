package myapp.tae.ac.uk.myfactorizerapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import myapp.tae.ac.uk.myfactorizerapp.presenter.FactorizerService;
import myapp.tae.ac.uk.myfactorizerapp.presenter.FactorizerView;

/**
 * Created by Karma on 10/03/16.
 */
public class BackgroundTask extends AsyncTask<Double, Integer, ArrayList<Double>> implements DialogInterface.OnClickListener {
    private ProgressDialog progressDialog;
    private boolean isRunning;
    private FactorizerService service;
    private FactorizerView factorizerView;

    public BackgroundTask(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", this);
        isRunning = false;
        service = new FactorizerService();
    }

    @Override
    protected void onPreExecute() {
        isRunning = true;
        progressDialog.show();
    }

    @Override
    protected ArrayList<Double> doInBackground(Double... params) {
        ArrayList<Double> factors = new ArrayList<>();
        double number = params[0];
        double originalNumber = number;
        int progress = 0;
        factors.add(1.0);
        int counter = 2;
        while (isRunning) {
            if (number % counter == 0) {
                number /= counter;
                factors.add((double) counter);
                progress = (int) (((originalNumber - number) / originalNumber) * 100);
                Log.i("AsyncTask", "doInBackground: Number = " + number + " and Factor = " + counter + " progress = " + progress);

                counter = 2;
                continue;
            }
            if (service.isPrimeNumber(number)) {
                factors.add(number);
                publishProgress(100);
                break;
            }
            publishProgress(progress);
            counter++;
        }
        Log.i("AsyncTask", "doInBackground: " + factors.toString());
        return factors;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);

    }

    @Override
    protected void onPostExecute(ArrayList<Double> factors) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        factorizerView.updateResult(service.convertToPrettyString(factors));
        isRunning = false;
    }

    public void setFactorizerView(FactorizerView factorizerView) {
        this.factorizerView = factorizerView;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        this.cancel(true);
        dialog.dismiss();
    }
}
