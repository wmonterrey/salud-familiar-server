package ni.gob.minsa.hsf.adapters;

import java.util.List;

import ni.gob.minsa.hsf.R;
import ni.gob.minsa.hsf.domain.Divisionpolitica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DivPoliticaAdapter extends ArrayAdapter<Divisionpolitica> {


	public DivPoliticaAdapter(Context context, int textViewResourceId,
			List<Divisionpolitica> items) {
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
		Divisionpolitica s = getItem(position);
		if (s != null) {

			TextView textView = (TextView) v.findViewById(R.id.label);
			if (textView != null) {
				textView.setText(String.valueOf(s.getNombre()));
			}
		}
		return v;
	}
}
