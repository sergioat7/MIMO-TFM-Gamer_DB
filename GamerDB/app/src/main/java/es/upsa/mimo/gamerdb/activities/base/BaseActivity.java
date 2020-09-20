/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/9/2020
 */

package es.upsa.mimo.gamerdb.activities.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import es.upsa.mimo.gamerdb.BuildConfig;
import es.upsa.mimo.gamerdb.fragments.popups.PopupErrorDialogFragment;
import es.upsa.mimo.gamerdb.models.ErrorResponse;
import es.upsa.mimo.gamerdb.utils.Constants;

public class BaseActivity extends AppCompatActivity {

    public void manageError(ErrorResponse errorResponse) {

        StringBuilder error = new StringBuilder();
        error.append(getResources().getString(errorResponse.getErrorKey()));
        if (BuildConfig.DEBUG) {

            error.append("\n");
            error.append(errorResponse.getInternalMessage());
        }
        showPopupDialog(error.toString());
    }

    public void showPopupDialog(String message) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(Constants.ERROR_POPUP_ID);
        if (prev != null) {
            transaction.remove(prev);
        }
        transaction.addToBackStack(null);
        PopupErrorDialogFragment dialogFragment = new PopupErrorDialogFragment(message);
        dialogFragment.setCancelable(false);
        dialogFragment.show(transaction, Constants.ERROR_POPUP_ID);
    }
}