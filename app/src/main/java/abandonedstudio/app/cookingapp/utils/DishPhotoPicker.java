package abandonedstudio.app.cookingapp.utils;

import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.Fragment;

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

    /* public void onResult(Bitmap bitmap, Context context){
        try {
            createFileAndFolder(bitmap, context);
        }catch (Exception e){
            //to do
        }
    } */

    /* private void createFileAndFolder(Bitmap bitmap, Context context) {
        //File directory = new File(Environment.DIRECTORY_PICTURES + File.separator + "dish_images");
        File directory = new File(context.getFilesDir(), "image_" + System.currentTimeMillis() + ".jpg");
        /*if(!directory.exists()){
            if(directory.mkdirs()) Log.d("Image_saving", directory.getAbsolutePath());
        }
        Log.d("Image_saving", directory.getAbsolutePath());
        // Objects.requireNonNull(fragment.requireContext().getExternalFilesDir(null)).getAbsolutePath()
        //File path = new File(directory, "image_" + System.currentTimeMillis() + ".jpg");
        //Log.d("Image_saving", path.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(directory)) {
            //bitmap saved to internal storage here
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            //comment
            fos.flush();
            setUri(Uri.parse(directory.getAbsolutePath()));
            Log.d("Image_saving", directory.getAbsolutePath());
        } catch (Exception e) {
            Log.d("Image_saving", "Error");
        }
    } /*


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
