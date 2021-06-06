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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplemovieapps.R;
import com.example.simplemovieapps.adapter.QotdDiscoverAdapter;
import com.example.simplemovieapps.model.QuoteResultItem;
import com.example.simplemovieapps.view.activity.MainActivity;
import com.example.simplemovieapps.view.viewmodel.QotdViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QotdFragment extends Fragment {

    Context context;
    private QotdDiscoverAdapter qotdDiscoverAdapter;
    private RecyclerView rvMovieDiscover;
    private QotdViewModel qotdViewModel;

    public QotdFragment(Context context) {
        // Required empty public constructor
        this.context=context;
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

        qotdDiscoverAdapter = new QotdDiscoverAdapter(context);
        // pakai get context karena memiliki parameter
        qotdDiscoverAdapter.notifyDataSetChanged();//ketika data berubah, tamoilan berubah

//        seting recycle viet
        rvMovieDiscover = view.findViewById(R.id.fragment_qotd);
        rvMovieDiscover.setLayoutManager(new GridLayoutManager(getContext(), 1)); // seting berapa kolom

        // view model
        qotdViewModel = new ViewModelProvider(this).get(QotdViewModel.class);
        qotdViewModel.setQuoteDiscover();

        qotdViewModel.getQotdDiscover().observe(getViewLifecycleOwner(),getQotdDiscover);//asal dari kelas ini
        // jika atas bawah sudah
        //adapternya diubah
        rvMovieDiscover.setAdapter(qotdDiscoverAdapter);
    }

    private Observer<ArrayList<QuoteResultItem>> getQotdDiscover = new Observer<ArrayList<QuoteResultItem>>() {
        @Override
        public void onChanged(ArrayList<QuoteResultItem> qotdDiscoverResultsItems) {
            //apabila data berubah maka berubah juga di recycle view
            if (qotdDiscoverResultsItems != null){
                // jika tidak null maka adapternya akan diubah lagi
                qotdDiscoverAdapter.setData(qotdDiscoverResultsItems);
            }
        }
    };


}
