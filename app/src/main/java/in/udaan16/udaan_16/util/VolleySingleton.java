package in.udaan16.udaan_16.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Creator: vbarad
 * Date: 2016-03-09
 * Project: udaan16-android-app
 */
public class VolleySingleton {
  private static VolleySingleton instance;
  private static Context context;
  private RequestQueue requestQueue;
  private ImageLoader imageLoader;

  public VolleySingleton(Context c) {
    context = c;
    requestQueue = getRequestQueue();

    imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
      private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(40);

      @Override
      public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
      }

      @Override
      public Bitmap getBitmap(String url) {
        return cache.get(url);
      }
    });
  }

  public static synchronized VolleySingleton getInstance(Context c) {
    if (instance == null) {
      instance = new VolleySingleton(c);
    }
    return instance;
  }

  public RequestQueue getRequestQueue() {
    if (requestQueue == null) {
      requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    return requestQueue;
  }

  public ImageLoader getImageLoader() {
    return imageLoader;
  }

  public <T> void addToRequestQueue(Request<T> req) {
    getRequestQueue().add(req);
  }
}
