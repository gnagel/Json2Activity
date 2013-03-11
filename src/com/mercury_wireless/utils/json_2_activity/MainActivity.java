package com.mercury_wireless.utils.json_2_activity;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


import org.json.JSONObject;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.mercury_wireless.utils.image_view_with_url.ImageViewWithUrlHandler;


public final class MainActivity extends Activity implements Thread.UncaughtExceptionHandler {
	private ImageViewWithUrlHandler	imageViewWithUrlHandler;


	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			final LinearLayout root = (LinearLayout) findViewById(R.id.LinearLayout);

			this.imageViewWithUrlHandler = new ImageViewWithUrlHandler(getApplication(), this);
			this.imageViewWithUrlHandler.onCreate();

			final ViewFactory factory = new ViewFactory(this, imageViewWithUrlHandler);
			final IamView view = factory.parse(new JSONObject(readRawTextFile()));
			view.addViewToParent(root, this, null);
		}
		catch (final Throwable e) {
			uncaughtException(Thread.currentThread(), e);
		}
	}


	@Override
	public final boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (null != this.imageViewWithUrlHandler) {
			this.imageViewWithUrlHandler.onDestroy();
		}
		this.imageViewWithUrlHandler = null;
	}


	public final String readRawTextFile()
	{
		final InputStream inputStream = getResources().openRawResource(R.raw.scroll_view);

		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1)
			{
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		}
		catch (final IOException e) {
			return null;
		}
		return byteArrayOutputStream.toString();
	}


	@Override
	public final void uncaughtException(final Thread thread, final Throwable e) {
		// Log the error
		Log.e(getPackageName(), e.getMessage(), e);

		// Must run on the UI thread!
		runOnUiThread(new Runnable() {
			@Override
			public final void run() {
				Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		});
	}
}
