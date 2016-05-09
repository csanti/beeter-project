package edu.upc.eetac.dsa.beeter;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;

import edu.upc.eetac.dsa.beeter.client.BeeterClient;
import edu.upc.eetac.dsa.beeter.client.BeeterClientException;
import edu.upc.eetac.dsa.beeter.client.entity.Sting;

public class StingDetailActivity extends AppCompatActivity {

    private Sting sting = new Sting();

    private final static String TAG = StingDetailActivity.class.toString();
    private GetStingTask mGetStingTask = null;

    TextView subject;
    TextView creator;
    TextView date;
    TextView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sting_detail);

        Bundle extras = getIntent().getExtras();

        mGetStingTask = new GetStingTask(extras.get("uri").toString());
        mGetStingTask.execute((Void) null);

        subject =(TextView)findViewById(R.id.subject);
        creator =(TextView)findViewById(R.id.creator);
        date =(TextView)findViewById(R.id.date);
        content =(TextView)findViewById(R.id.content);

        subject.setTypeface(null, Typeface.BOLD);
        creator.setTypeface(null, Typeface.BOLD);
        date.setTypeface(null, Typeface.BOLD);
        content.setTypeface(null, Typeface.BOLD);

    }

    class GetStingTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetStingTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonSting = null;
            try {
                jsonSting = BeeterClient.getInstance().getSting(uri);
            } catch (BeeterClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonSting;
        }

        @Override
        protected void onPostExecute(String jsonStingCollection) {
            Log.d(TAG, jsonStingCollection);
            sting = (new Gson()).fromJson(jsonStingCollection, Sting.class);
            subject.setText(sting.getSubject());
            creator.setText(sting.getCreator());
            date.setText(new SimpleDateFormat("MM/dd/yyyy").format(sting.getCreationTimestamp()));

        }
    }
}
