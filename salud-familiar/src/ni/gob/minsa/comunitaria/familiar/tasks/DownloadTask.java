package ni.gob.minsa.comunitaria.familiar.tasks;

import ni.gob.minsa.comunitaria.familiar.database.FamiliarAdapter;
import ni.gob.minsa.comunitaria.familiar.listener.DownloadListener;
import android.os.AsyncTask;

public abstract class DownloadTask extends
AsyncTask<String, String, String> {

	protected static final String TAG = DownloadTask.class.getSimpleName();

	protected DownloadListener mStateListener;
	protected FamiliarAdapter va = new FamiliarAdapter();

	@Override
	protected void onProgressUpdate(String... values) {
		synchronized (this) {
			if (mStateListener != null) {
				// update progress and total
				mStateListener.progressUpdate(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]));
			}
		}

	}

	@Override
	protected void onPostExecute(String result) {
		synchronized (this) {
			if (mStateListener != null)
				mStateListener.downloadComplete(result);
		}
	}

	public void setDownloadListener(DownloadListener sl) {
		synchronized (this) {
			mStateListener = sl;
		}
	}
}

