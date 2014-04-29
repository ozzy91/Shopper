package com.ostfeld.shopper.app.tasks;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.ostfeld.shopper.app.DataManager;
import com.ostfeld.shopper.app.model.Item;
import com.ostfeld.shopper.backend.listApi.ListApi;

import java.io.IOException;

/**
 * Created by thomas on 24.04.14.
 */
public class SetCheckedTask extends AsyncTask<Item, Void, Void> {

    private ListApi listApiService;

    public SetCheckedTask() {
        ListApi.Builder builder = new ListApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
//                .setRootUrl(DataManager.SERVER_ADDRESS + DataManager.API_ADDRESS)
//                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                    @Override
//                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                        abstractGoogleClientRequest.setDisableGZipContent(true);
//                    }
//                });

        listApiService = builder.build();
    }

    @Override
    protected Void doInBackground(Item... params) {
        Item item = params[0];
        try {
            listApiService.update(item.getId(), item.isChecked()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
