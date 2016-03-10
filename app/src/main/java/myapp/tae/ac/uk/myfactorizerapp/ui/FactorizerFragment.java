package myapp.tae.ac.uk.myfactorizerapp.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapp.tae.ac.uk.myfactorizerapp.R;
import myapp.tae.ac.uk.myfactorizerapp.presenter.FactorizerPresenter;
import myapp.tae.ac.uk.myfactorizerapp.presenter.FactorizerService;
import myapp.tae.ac.uk.myfactorizerapp.presenter.FactorizerView;

/**
 * Created by Karma on 10/03/16.
 */
public class FactorizerFragment extends Fragment implements FactorizerView {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        ButterKnife.bind(this, view);
        presenter = new FactorizerPresenter(this, new FactorizerService());
        setupProgressDialog();
        return view;
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Factorization is in Progress");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
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
    public void showCancelledToast(int resId) {
        Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    @Override
    public void updateResult(String result) {
        tvResultTitle.setText("Factors of " + etInputEntry.getText() + " are:");
        tvResult.setText(result);
    }
}

