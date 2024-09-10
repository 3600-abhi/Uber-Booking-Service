package com.uber.BookingService.config;

import com.uber.BookingService.apis.LocationServiceApi;
import com.uber.BookingService.apis.SocketServiceApi;
import com.uber.BookingService.constant.AppConstant;
import com.uber.BookingService.utility.Utils;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    private final Utils utils;

    public RetrofitConfig(Utils utils) {
        this.utils = utils;
    }


    @Bean
    public LocationServiceApi locationServiceApi() {
        return new Retrofit.Builder()
                .baseUrl(utils.getConfigValue(AppConstant.LOCATION_SERVICE_URL_CONFIG_PATH)) // path only take till port number ie : http://127.0.0.1:8004/ from http://127.0.0.1:8004/api/v1/location/
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(LocationServiceApi.class);
    }

    @Bean
    public SocketServiceApi socketServiceApi() {
        return new Retrofit.Builder()
                .baseUrl(utils.getConfigValue(AppConstant.SOCKET_SERVICE_URL_CONFIG_PATH)) // path only take till port number ie : http://127.0.0.1:8004/ from http://127.0.0.1:8004/api/v1/location/
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(SocketServiceApi.class);
    }
}
