package com.android.daggerfullprojectwithmvvm.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;


public class CheckConnectivity {

    public CheckConnectivity() {
        super();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true;
                    }
                }
            } else {
                try {
                    ConnectivityManager
                            cm = (ConnectivityManager) context
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = null;
                    if (cm != null) {
                        activeNetwork = cm.getActiveNetworkInfo();
                    }
                    if (activeNetwork != null
                            && activeNetwork.isConnectedOrConnecting() && activeNetwork.isConnected()) {
                        return true;
                    }
                } catch (Exception e) {
                }
            }
        }
        return false;
    }

}