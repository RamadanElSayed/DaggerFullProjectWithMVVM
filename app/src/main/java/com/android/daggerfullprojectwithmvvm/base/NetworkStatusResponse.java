package com.android.daggerfullprojectwithmvvm.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NetworkStatusResponse<T> {
    public enum LoginStatus {AUTHENTICATED, LOADING, ERROR, NOT_AUTHENTICATED}

    @NonNull
    public final LoginStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public NetworkStatusResponse(@NonNull LoginStatus status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> NetworkStatusResponse<T> authenticated(T data) {
        return new NetworkStatusResponse<>(LoginStatus.AUTHENTICATED, data, null);
    }

    public static <T> NetworkStatusResponse<T> loading(T data) {
        return new NetworkStatusResponse<>(LoginStatus.LOADING, data, null);
    }

    public static <T> NetworkStatusResponse<T> error(String message, T data) {
        return new NetworkStatusResponse<>(LoginStatus.ERROR, data, message);
    }

    public static <T> NetworkStatusResponse<T> logout() {
        return new NetworkStatusResponse<>(LoginStatus.NOT_AUTHENTICATED, null, null);
    }

}
