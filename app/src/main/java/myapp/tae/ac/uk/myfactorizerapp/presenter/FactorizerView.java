package myapp.tae.ac.uk.myfactorizerapp.presenter;

/**
 * Created by Karma on 10/03/16.
 */
public interface FactorizerView {
    String getInputEntry();

    void showInvalidEntryError(int resId);

    void showEmptyEntryError(int resId);

    boolean cancelFactorizationProgress();

    void showCancelledToast(int resId);
}
