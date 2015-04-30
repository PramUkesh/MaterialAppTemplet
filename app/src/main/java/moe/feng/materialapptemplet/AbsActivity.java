package moe.feng.materialapptemplet;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public abstract class AbsActivity extends ToolbarActivity {

	private FrameLayout mContentLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public void setContentView(@LayoutRes int layoutResId) {
		super.setContentView(R.layout.abs_activity);

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

		mContentLayout.addView(View.inflate(getApplicationContext(), layoutResId, null));
	}

	protected void setUpViews() {
		mContentLayout = (FrameLayout) findViewById(R.id.content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
