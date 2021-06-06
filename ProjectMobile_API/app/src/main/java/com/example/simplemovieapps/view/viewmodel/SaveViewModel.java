package com.example.simplemovieapps.view.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplemovieapps.model.QuoteResponse;
import com.example.simplemovieapps.model.QuoteResultItem;
import com.example.simplemovieapps.model.SaveModel;
import com.example.simplemovieapps.service.ApiMain;
import com.example.simplemovieapps.service.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveViewModel extends ViewModel {

    private MutableLiveData<ArrayList<SaveModel>> listDiscoverQotd = new MutableLiveData<>(); // inilah yang akan dikirim ke fragmen
    private ArrayList<SaveModel> saveModels = new ArrayList<>();

    public void setQuoteSave(Context context, int user) {
    //ambil data database



        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        ArrayList<SaveModel> saveModels = databaseHelper.getQuoteList(user);

        listDiscoverQotd.postValue(saveModels);

    }

    // karena menggunakan live data
    public LiveData<ArrayList<SaveModel>> getQuoteSave(){
        return listDiscoverQotd;
    }

}
