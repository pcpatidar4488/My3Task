package generatebarcode.com.newtask.network;

import generatebarcode.com.newtask.network.api_request.ApiRequest;
import generatebarcode.com.newtask.network.api_response.ApiResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by punamchand on 18-Oct-18.
 */

public interface Api {
    @POST("api_test/")
    Call<ApiResponse> sendImage(@Body ApiRequest payload);
}
