package abandonedstudio.app.cookingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class DishPhotoPicker {

    private  Uri uri;

    public DishPhotoPicker() {
        //empty constructor
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void pickDishPhoto(Fragment fragment){
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        fragment.startActivityForResult(chooserIntent, RequestCode.GALLERY_PICK_IMAGE_REQUEST_CODE);
    }

    public void onResult(Bitmap bitmap){
        try {
            createFileAndFolder(bitmap);
        }catch (Exception e){
            //to do
        }
    }

    private void createFileAndFolder(Bitmap bitmap) {
        File directory = new File(Environment.DIRECTORY_PICTURES + File.separator + "dish_images");
        if(!directory.exists()){
            directory.mkdirs();
        }
        // Objects.requireNonNull(fragment.requireContext().getExternalFilesDir(null)).getAbsolutePath()
        File path = new File(directory, "image_" + System.currentTimeMillis() + ".jpg");

        try (FileOutputStream fos = new FileOutputStream(path)) {
            //bitmap saved to internal storage here
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            //comment
            setUri(Uri.parse(path.getAbsolutePath()));
            Log.d("Image_saving", path.getAbsolutePath());
        } catch (Exception e) {
            Log.d("Image_saving", "Error");
        }
    }

    // saving image
    /* private void writeStreamToFile(InputStream input, File file) {
        try {
            try (OutputStream output = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024]; // or other buffer size
                int read;
                while ((read = input.read(buffer)) != -1) {
                    output.write(buffer, 0, read);
                }
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } */

}
