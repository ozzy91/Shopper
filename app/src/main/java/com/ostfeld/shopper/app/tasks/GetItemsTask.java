package com.ostfeld.shopper.app.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.ostfeld.shopper.app.DataManager;
import com.ostfeld.shopper.app.interfaces.ListManager;
import com.ostfeld.shopper.backend.listApi.ListApi;
import com.ostfeld.shopper.backend.listApi.model.ListItem;

import java.io.IOException;
import java.util.List;

/**
 * Created by thomas on 24.04.14.
 */
public class GetItemsTask extends AsyncTask<Void, Void, List<ListItem>> {

    private ListApi listApiService;
    private ListManager listManager;

    public GetItemsTask(ListManager listManager) {
        ListApi.Builder builder = new ListApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
//                .setRootUrl(DataManager.SERVER_ADDRESS + DataManager.API_ADDRESS)
//                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                    @Override
//                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                        abstractGoogleClientRequest.setDisableGZipContent(true);
//                    }
//                });

        listApiService = builder.build();

        this.listManager = listManager;
    }

    @Override
    protected List<ListItem> doInBackground(Void... params) {
        try {
            return listApiService.list().execute().getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<ListItem> listItems) {
        super.onPostExecute(listItems);

        if (listManager != null) {
            listManager.receiveList(listItems);
        }
    }
}
