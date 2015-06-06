package shaolinspiders.socialproblems;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by dimmat97 on 6/6/15.
 */
public class Camera extends ActionBarActivity {

    Button next;
    Button tryAgain;
    ImageView showImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        setIds();
        addListeners();
        //takePicture();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void logout() {
        getSharedPreferences("token", MODE_PRIVATE).edit().clear().apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


    private void addListeners() {
        next.setOnClickListener(nextListener);
        tryAgain.setOnClickListener(tryAgainListener);
    }

    View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener tryAgainListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            takePicture();
        }
    };

    private void takePicture(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    private void setIds() {
        next = (Button) findViewById(R.id.nextcamera);
        tryAgain = (Button) findViewById(R.id.tryagaincamera);
        showImage = (ImageView) findViewById(R.id.taken_picture);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                showImage.setImageBitmap(image);
            }
        }
    }
}
