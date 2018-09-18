package gka.myphisixtest.network;

import gka.myphisixtest.network.models.ResponseModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IAPI {

    @GET("stocks.json")
    Observable<ResponseModel> getList();
}
