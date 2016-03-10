package myapp.tae.ac.uk.myfactorizerapp.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import myapp.tae.ac.uk.myfactorizerapp.R;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Karma on 10/03/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class FactorizerPresenterTest {
    @Mock
    private FactorizerView view;
    @Mock
    private FactorizerService service;
    private FactorizerPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new FactorizerPresenter(view, service);
    }

    @Test
    public void showErrorOnEmptyEntry() throws Exception {
        when(view.getInputEntry()).thenReturn("");
        presenter.onFactorizeButtonClicked();

        verify(view).showEmptyEntryError(R.string.error_empty_entry);

    }

    @Test
    public void showErrorOnInvalidEntry() throws Exception {
        when(view.getInputEntry()).thenReturn("letter");
        presenter.onFactorizeButtonClicked();

        verify(view).showInvalidEntryError(R.string.error_invalid_entry);
    }

    @Test
    public void showMessageOnCancelledProgress() throws Exception {
        when(view.getInputEntry()).thenReturn("20");
        presenter.onFactorizeButtonClicked();
        when(view.cancelFactorizationProgress()).thenReturn(true);

        verify(view).showCancelledToast(R.string.progress_cancel);
    }
}