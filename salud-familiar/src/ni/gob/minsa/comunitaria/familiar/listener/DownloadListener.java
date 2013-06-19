package ni.gob.minsa.comunitaria.familiar.listener;
public interface DownloadListener {
	void downloadComplete(String result);
	void progressUpdate(String message, int progress, int max);
}
