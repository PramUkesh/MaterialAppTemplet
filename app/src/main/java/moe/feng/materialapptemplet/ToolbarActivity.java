package moe.feng.materialapptemplet;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import moe.feng.materialapptemplet.support.Settings;
import moe.feng.materialapptemplet.support.Utility;

public abstract class ToolbarActivity extends AppCompatActivity {

	protected Toolbar mToolbar;
	protected ActionBar mActionBar;
	protected Settings mSets;

	protected int statusBarHeight = 0;

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/** Initialize Settings */
		mSets = Settings.getInstance(getApplicationContext());

		/** Set up translucent status bar */
		if (Build.VERSION.SDK_INT >= 19 && !Utility.isChrome()) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			statusBarHeight = Utility.getStatusBarHeight(getApplicationContext());
		}

		if (Build.VERSION.SDK_INT >= 21) {
			getWindow().setStatusBarColor(Color.TRANSPARENT);
			if (mSets.getBoolean(Settings.KEY_NAVIGATION_TINT, true)) {
				getWindow().setNavigationBarColor(getResources().getColor(R.color.blue_800));
			}
		}

		super.onCreate(savedInstanceState);
	}

	protected abstract void setUpViews();

	@Override
	public void setContentView(@LayoutRes int layoutResId) {
		super.setContentView(layoutResId);

		try {
			View statusHeaderView = findViewById(R.id.statusHeaderView);
			statusHeaderView.getLayoutParams().height = statusBarHeight;
		} catch (NullPointerException e) {

		}

		try {
			mToolbar = (Toolbar) findViewById(R.id.toolbar);
			setSupportActionBar(mToolbar);
		} catch (Exception e) {

		}
		mActionBar = getSupportActionBar();

		setUpViews();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			this.onBackPressed();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
