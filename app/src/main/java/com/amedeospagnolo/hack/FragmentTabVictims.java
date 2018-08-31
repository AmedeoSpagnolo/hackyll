package com.amedeospagnolo.hack;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentTabVictims extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_victims, container, false);

        // add fake data
        ArrayList<DataVictim> myFakeDataset = new ArrayList<DataVictim>();
        VictimAdapterList adapter = new VictimAdapterList(this.getContext(), myFakeDataset);
        adapter.add(new DataVictim("Hi, Nathan!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Bob!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Francene!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Cammie!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Ines!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Celeste!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Renata!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Stephan!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Marcia!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Romona!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Carter!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Roslyn!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Marshall!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Donnell!", "Hong Kong"));
        adapter.add(new DataVictim("Hi, Sigrid!", "Hong Kong"));

        ListView listView = rootView.findViewById(R.id.list_clients);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
//              String selectedmovie=myFakeDataset.get(position);
//              Toast.makeText(getApplicationContext(), "Movie Selected : "+selectedmovie,   Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    public class VictimAdapterList extends ArrayAdapter<DataVictim> {
        public VictimAdapterList(Context context, ArrayList<DataVictim> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataVictim my_items = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.carditem_victim, parent, false);
            }
            TextView clName = convertView.findViewById(R.id.client_name);
            TextView clIp = convertView.findViewById(R.id.client_ip);
            clName.setText(my_items.name);
            clIp.setText(my_items.ip);
            return convertView;
        }
    }

}