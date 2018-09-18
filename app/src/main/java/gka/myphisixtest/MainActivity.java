package gka.myphisixtest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import gka.myphisixtest.network.API;
import gka.myphisixtest.network.models.ItemModel;
import gka.myphisixtest.network.models.ResponseModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    API api;


    RecyclerView rvList;
    View pb;

    LinearLayoutManager llm;
    MyAdapter adapter;

    ArrayList<ItemModel> stock;

    CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = findViewById(R.id.rvList);
        pb = findViewById(R.id.pb);

        api = new API();
        compositeDisposable = new CompositeDisposable();

        pb.setVisibility(View.VISIBLE);


        setupList();

        requestData();

    }

    private void setupList() {

        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(llm);

        stock = new ArrayList<>();

        adapter = new MyAdapter(stock);
        rvList.setAdapter(adapter);
    }

    CountDownTimer countDownTimer = new CountDownTimer(
            TimeUnit.SECONDS.toMillis(15),
            TimeUnit.SECONDS.toMillis(15)) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            requestData();
        }
    };
    Disposable di;


    private void requestData() {
        api.getList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        di = d;
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseModel response) {
                        setList(response.getStock());
                    }

                    @Override
                    public void onError(Throwable e) {
                        countDownTimer.start();

                        pb.setVisibility(View.GONE);

                    }

                    @Override
                    public void onComplete() {
                        countDownTimer.start();

                        pb.setVisibility(View.GONE);

                        compositeDisposable.clear();
                    }
                });

    }

    private void setList(ArrayList<ItemModel> stock) {
        this.stock.clear();
        this.stock.addAll(stock);

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_update, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                onUpdateClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void onUpdateClick() {
        if (compositeDisposable.size() != 0) return;

        countDownTimer.cancel();
        requestData();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
