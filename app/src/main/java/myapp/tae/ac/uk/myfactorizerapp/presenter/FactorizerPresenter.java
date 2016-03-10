package myapp.tae.ac.uk.myfactorizerapp.presenter;

import android.widget.EditText;

import java.util.ArrayList;

import myapp.tae.ac.uk.myfactorizerapp.R;
import myapp.tae.ac.uk.myfactorizerapp.ui.BackgroundTask;

/**
 * Created by Karma on 10/03/16.
 */
public class FactorizerPresenter {
    FactorizerView view;
    FactorizerService service;

    public FactorizerPresenter(FactorizerView view, FactorizerService factorizerService) {
        this.view = view;
        this.service = service;
    }


    public void onFactorizeButtonClicked() {
        String inptEntry = view.getInputEntry();
        if (inptEntry.isEmpty()) {
            view.showEmptyEntryError(R.string.error_empty_entry);
            return;
        }

        if (!inptEntry.matches("^\\d+(\\.?\\d*)?")) {
            view.showInvalidEntryError(R.string.error_invalid_entry);
            return;
        }
        double number = Double.parseDouble(inptEntry);
        factorizeInBackground(number);
    }


    public void factorizeInBackground(double number) {
        BackgroundTask task = new BackgroundTask(view.getProgressDialog());
        task.setFactorizerView(view);
        task.execute(number);

    }
}
