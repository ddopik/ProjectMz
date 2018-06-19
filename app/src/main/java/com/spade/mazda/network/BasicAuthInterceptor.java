
package com.spade.mazda.network;

import android.content.Context;

import com.spade.mazda.utils.PrefUtils;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;
    private Context context;

    public BasicAuthInterceptor(Context context) {
        this.context = context;
        this.credentials = Credentials.basic(ApiHelper.VALIDATE_LOGIN_USER_NAME, ApiHelper.VALIDATE_LOGIN_PASSWORD);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
String s=PrefUtils.getUserToken(context);
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials)
                .addHeader("Authorization", "bearer " + PrefUtils.getUserToken(context))
                .build();
        return chain.proceed(authenticatedRequest);
    }


}

