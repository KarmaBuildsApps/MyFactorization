package myapp.tae.ac.uk.myfactorizerapp.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapp.tae.ac.uk.myfactorizerapp.R;
import myapp.tae.ac.uk.myfactorizerapp.presenter.FactorizerView;
import myapp.tae.ac.uk.myfactorizerapp.presenter.FactorizerPresenter;
import myapp.tae.ac.uk.myfactorizerapp.presenter.FactorizerService;

public class MainFactorizer extends AppCompatActivity implements FactorizerView {
    @Bind(R.id.etInputEntry)
    EditText etInputEntry;
    @Bind(R.id.tvResultTitle)
    TextView tvResultTitle;
    @Bind(R.id.tvResult)
    TextView tvResult;
    @Bind(R.id.btFactorize)
    Button btFactorize;
    private FactorizerPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new FactorizerPresenter(this, new FactorizerService());
        setupProgressDialog();
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Factorization is in Progress");
        progressDialog.setCancelable(true);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                presenter.cancelBackgroundTask();
            }
        });
    }

    @OnClick(R.id.btFactorize)
    public void onButtonClicked(View view) {
        presenter.onFactorizeButtonClicked();
    }

    @Override
    public String getInputEntry() {
        return etInputEntry.getText().toString();
    }

    @Override
    public void showInvalidEntryError(int resId) {
        etInputEntry.setError(getString(resId));
    }

    @Override
    public void showEmptyEntryError(int resId) {
        etInputEntry.setError(getString(resId));
    }

    @Override
    public boolean cancelFactorizationProgress() {
        presenter.cancelBackgroundTask();
        return true;
    }

    @Override
    public void showCancelledToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }
}
