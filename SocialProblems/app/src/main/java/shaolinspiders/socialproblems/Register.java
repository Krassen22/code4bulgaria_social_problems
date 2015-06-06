package shaolinspiders.socialproblems;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import http.HttpPostRequest;


/**
 * Created by dimmat97 on 5/8/15.
 */
public class Register extends ActionBarActivity {

    Button submit_button;
    EditText username_et;
    EditText password_et;
    EditText password_conf_et;
    EditText firstName_et;
    EditText secondName_et;
    EditText lastName_et;
    EditText town_et;
    EditText address_et;

    private String username;
    private String password;
    private String conf_password;
    private String firstName;
    private String secondName;
    private String lastName;
    private String town;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        setViews();
        submit_button.setOnClickListener(register_listener);
    }

    private void get_input() {
        username = username_et.getText().toString();
        password = password_et.getText().toString();
        conf_password = password_conf_et.getText().toString();
        firstName = firstName_et.getText().toString();
        secondName = secondName_et.getText().toString();
        lastName = lastName_et.getText().toString();
        town = town_et.getText().toString();
        address = address_et.getText().toString();
    }
    private void setViews() {
        submit_button = (Button) findViewById(R.id.account_button);
        username_et = (EditText) findViewById(R.id.register_username);
        password_et = (EditText) findViewById(R.id.register_password);
        password_conf_et = (EditText) findViewById(R.id.register_password_confirm);
        firstName_et = (EditText) findViewById(R.id.register_firstName);
        secondName_et = (EditText) findViewById(R.id.register_secondName);
        lastName_et = (EditText) findViewById(R.id.register_lastName);
        town_et = (EditText) findViewById(R.id.register_town);
        address_et = (EditText) findViewById(R.id.register_address);
    }

    View.OnClickListener register_listener = new View.OnClickListener() {
        public void onClick(View v) {
            get_input();
            String url = getResources().getString(R.string.website_url_register); // register
            new HttpRequest().execute(url);
        }
    };

    private void handle_action(String message) {
        if(!message.equals("Success")) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public class HttpRequest extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            JSONObject params = new JSONObject();
            try {
                params.put("email", username);
                params.put("password", password);
                params.put("confirm_password", conf_password);
                params.put("firstName",firstName);
                params.put("secondName", secondName);
                params.put("lastName", lastName);
                params.put("town", town);
                params.put("address", address);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return HttpPostRequest.make_request(uri[0], params);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            handle_action(result);
        }
    }
}