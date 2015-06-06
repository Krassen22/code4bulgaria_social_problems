package shaolinspiders.socialproblems;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dimmat97 on 6/6/15.
 */
public class Camera extends Activity {

    Button next;
    Button tryAgain;
    ImageView showImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        setIds();
        addListeners();
        takePicture();
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
