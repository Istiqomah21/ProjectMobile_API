package com.example.simplemovieapps.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplemovieapps.model.QuoteResponse;
import com.example.simplemovieapps.model.QuoteResultItem;
import com.example.simplemovieapps.service.ApiMain;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QotdViewModel extends ViewModel {
    private ApiMain apiMain;
    private int count;

    private MutableLiveData<ArrayList<QuoteResultItem>> listDiscoverQotd = new MutableLiveData<>(); // inilah yang akan dikirim ke fragmen
    private ArrayList<QuoteResultItem> qotdDiscoverItems = new ArrayList<>();

    public void setQuoteDiscover() {
        for (count = 0; count < 10; count++) {
            apiMain = new ApiMain();

            apiMain.getApiQotd().getData().enqueue(new Callback<QuoteResponse>() {

                @Override
                public void onResponse(Call<QuoteResponse> call, Response<QuoteResponse> response) {
//                 cek apakah ada response
//                 jika ada , data json dipindah ke sini
                    QuoteResponse responseDiscover = response.body();
                    // cek apakah datanya null biar aman
                    if (responseDiscover != null && responseDiscover.getQuote() != null) {
                        // masukan ke variabel

                        QuoteResultItem item = responseDiscover.getQuote();
                        qotdDiscoverItems.add(item);
                        //disini
                        if(count==10) {
                            listDiscoverQotd.postValue(qotdDiscoverItems);
                        }
                    }
                }

                @Override
                public void onFailure(Call<QuoteResponse> call, Throwable t) {

                    List<String> list = new ArrayList<String>();
                    //Adding elements in the List
                    list.add("Mango");
                    list.add("Apple");
                    qotdDiscoverItems.add(new QuoteResultItem(true, 1, "autor gagal", true, 1, "wfewf", 41, "apa isinya", "fef", list, 43));

                    //disini
                    if(count==10) {
                        listDiscoverQotd.postValue(qotdDiscoverItems);
                    }
                }

            });

        }
    }


// karena menggunakan live data
    public LiveData<ArrayList<QuoteResultItem>> getQotdDiscover(){
        return listDiscoverQotd;
    }

}
