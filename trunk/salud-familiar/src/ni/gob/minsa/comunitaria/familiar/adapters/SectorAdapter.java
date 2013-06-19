package ni.gob.minsa.comunitaria.familiar.adapters;

import java.util.List;

import ni.gob.minsa.comunitaria.familiar.R;
import ni.gob.minsa.comunitaria.familiar.domain.Sector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SectorAdapter extends ArrayAdapter<Sector> {


	public SectorAdapter(Context context, int textViewResourceId,
			List<Sector> items) {
		super(context, textViewResourceId, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item, null);
		}
		Sector sector = getItem(position);
		if (sector != null) {

			TextView textView = (TextView) v.findViewById(R.id.label);
			if (textView != null) {
				textView.setText(String.valueOf(sector.getNombre()));
			}
		}
		return v;
	}
}
