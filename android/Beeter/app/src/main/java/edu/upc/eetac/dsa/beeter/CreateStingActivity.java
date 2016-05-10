package edu.upc.eetac.dsa.beeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import edu.upc.eetac.dsa.beeter.client.BeeterClient;
import edu.upc.eetac.dsa.beeter.client.BeeterClientException;
import edu.upc.eetac.dsa.beeter.client.entity.Sting;

public class CreateStingActivity extends AppCompatActivity {

    private EditText subject;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sting);
    }

    public void crearSting(View view) {
        subject = (EditText) findViewById(R.id.subject);
        content = (EditText) findViewById(R.id.content);

        if(subject != null && content != null)
        {
            Sting sting = new Sting();
            sting.setContent(content.getText().toString());
            sting.setSubject(subject.getText().toString());
            try{
                if(BeeterClient.getInstance().createSting(sting));
                    this.finish();
            }
            catch(BeeterClientException e) {
                Log.d("CREATE STING", e.getMessage());
            }
        }



    }
}
