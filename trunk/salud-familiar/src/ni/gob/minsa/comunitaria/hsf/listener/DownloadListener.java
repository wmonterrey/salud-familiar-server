package ni.gob.minsa.comunitaria.hsf.listener;
public interface DownloadListener {
	void downloadComplete(String result);
	void progressUpdate(String message, int progress, int max);
}
