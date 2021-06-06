package com.example.simplemovieapps.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.ConditionVariable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplemovieapps.R;
import com.example.simplemovieapps.adapter.QotdDiscoverAdapter;
import com.example.simplemovieapps.adapter.SaveAdapter;
import com.example.simplemovieapps.model.QuoteResultItem;
import com.example.simplemovieapps.model.SaveModel;
import com.example.simplemovieapps.view.viewmodel.QotdViewModel;
import com.example.simplemovieapps.view.viewmodel.SaveViewModel;

import java.util.ArrayList;

public class SaveFragment extends Fragment {
    Context context;
    private SaveAdapter saveAdapter;
    private RecyclerView rvQuoteSave;
    private SaveViewModel saveViewModel;
    Integer res, res_null;
    int user;
    public SaveFragment(Context context, int user, int res , int res_null) {
        // Required empty public constructor
        this.context=context;
        this.user=user;
        this.res=res;
        this.res_null=res_null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qotd, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // inisiasi adapter
        super.onViewCreated(view, savedInstanceState);

        saveAdapter = new SaveAdapter(context);
        // pakai get context karena memiliki parameter
        saveAdapter.notifyDataSetChanged();//ketika data berubah, tamoilan berubah

//        seting recycle viet
        rvQuoteSave = view.findViewById(R.id.fragment_qotd);
        rvQuoteSave.setLayoutManager(new GridLayoutManager(getContext(), 1)); // seting berapa kolom

        // view model
        saveViewModel = new ViewModelProvider(this).get(SaveViewModel.class);
        saveViewModel.setQuoteSave(context,user);

        saveViewModel.getQuoteSave().observe(getViewLifecycleOwner(),getQuote);//asal dari kelas ini
        // jika atas bawah sudah
        //adapternya diubah
        rvQuoteSave.setAdapter(saveAdapter);
    }


    private Observer<ArrayList<SaveModel>> getQuote = new Observer<ArrayList<SaveModel>>() {
        @Override
        public void onChanged(ArrayList<SaveModel> saveResultItems) {
            //apabila data berubah maka berubah juga di recycle view
            if (saveResultItems != null){
                // jika tidak null maka adapternya akan diubah lagi
                saveAdapter.setData(saveResultItems, res, res_null);
            }
        }
    };


}
