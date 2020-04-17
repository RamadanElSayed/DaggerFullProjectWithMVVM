package com.android.daggerfullprojectwithmvvm.authentication.view;
import androidx.databinding.DataBindingUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.daggerfullprojectwithmvvm.R;
import com.android.daggerfullprojectwithmvvm.base.BaseActivity;
import com.android.daggerfullprojectwithmvvm.databinding.ActivityLoginBinding;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding activityLoginBinding;

    @Inject
    LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        setupToolbar(activityLoginBinding.toolBar.toolBar);
        activityLoginBinding.toolBar.layoutBackArrow.setOnClickListener(v -> onBackPressed());
        // addFragment(activityLoginBinding.container.getId(),loginFragment);
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
