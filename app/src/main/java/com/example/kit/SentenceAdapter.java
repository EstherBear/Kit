//self-defined ArrayAdapter for sentence
package com.example.kit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.kit.Sentence;

import java.util.ArrayList;

public class SentenceAdapter extends ArrayAdapter<Sentence> {

    public SentenceAdapter(Activity context, ArrayList<Sentence> sentences) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for a TextView and a music resource ID, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, sentences);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_textview, parent, false);
        }

        // Get the {@link Sentence} object located at this position in the list
        Sentence currentSentence = getItem(position);

        // Find the TextView in the activity_textview.xml layout with the ID version_name
        TextView myTextView = (TextView) listItemView.findViewById(R.id.label);
        // Get the version name from the current Sentence object and
        // set this text on the TextView called label in the activity_textview.xml
        myTextView.setText(currentSentence.getDisplay());


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
