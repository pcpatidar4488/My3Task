package generatebarcode.com.newtask.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import generatebarcode.com.newtask.R;
import generatebarcode.com.newtask.activity.MainActivity;
import generatebarcode.com.newtask.network.ApiCallServices;
import generatebarcode.com.newtask.network.api_request.ApiRequest;
import generatebarcode.com.newtask.utils.Cv;
import generatebarcode.com.newtask.utils.Helpers;
import generatebarcode.com.newtask.utils.ImagePicker;

import static android.app.Activity.RESULT_OK;

/**
 * Created by punamchand on 18-Oct-18.
 */

public class FirstFragment extends Fragment {
    View view;
    CheckBox mCheckbox;
    CheckBox mCheckbox1;
    CheckBox mCheckbox2;
    CheckBox mCheckbox3;
    ImageView mImage;
    Button mBrowseImage;
    Button mUploadImage;
    FrameLayout mConstraintLayout;
    private Uri imageToUploadUri;
    public static Bitmap photo;
    public static String encodedString = "";
    public static ProgressDialog mProgressDialog;
   // static String imagePath = "/storage/sdcard0/Pictures/image.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.test_fragment_first, container, false);
        mCheckbox = view.findViewById(R.id.checkbox);
        mCheckbox1 = view.findViewById(R.id.checkbox1);
        mCheckbox2 = view.findViewById(R.id.checkbox2);
        mCheckbox3 = view.findViewById(R.id.checkbox3);
        mImage = view.findViewById(R.id.image);
        mBrowseImage = view.findViewById(R.id.brows_image);
        mUploadImage = view.findViewById(R.id.upload_image);
        mConstraintLayout = view.findViewById(R.id.frameLayout);
        mCheckbox.bringToFront();
        mCheckbox1.bringToFront();
        mCheckbox2.bringToFront();
        mCheckbox3.bringToFront();
        if (MainActivity.checkBox) {
            mCheckbox.setButtonDrawable(R.drawable.checkbo_check);
        } else {
            mCheckbox.setButtonDrawable(R.drawable.checkbox_uncheck);
        }
        if (MainActivity.checkBox1) {
            mCheckbox1.setButtonDrawable(R.drawable.checkbo_check);
        } else {
            mCheckbox1.setButtonDrawable(R.drawable.checkbox_uncheck);
        }
        if (MainActivity.checkBox2) {
            mCheckbox2.setButtonDrawable(R.drawable.checkbo_check);
        } else {
            mCheckbox2.setButtonDrawable(R.drawable.checkbox_uncheck);
        }
        if (MainActivity.checkBox3) {
            mCheckbox3.setButtonDrawable(R.drawable.checkbo_check);
        } else {
            mCheckbox3.setButtonDrawable(R.drawable.checkbox_uncheck);
        }
        if (photo != null) {
            mImage.setImageBitmap(photo);
        }/*else {
            Drawable dr = ((ImageView) mImage).getDrawable();
            Bitmap bitmap = ((BitmapDrawable) dr.getCurrent()).getBitmap();
            photo = bitmap;
            encodedString = Helpers.bitmapToBase64(bitmap);
        }*/

        mCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckbox.isChecked()) {
                    MainActivity.checkBox = true;
                    mCheckbox.setButtonDrawable(R.drawable.checkbo_check);
                } else {
                    MainActivity.checkBox = false;
                    mCheckbox.setButtonDrawable(R.drawable.checkbox_uncheck);
                }
            }
        });

        mCheckbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckbox1.isChecked()) {
                    MainActivity.checkBox1 = true;
                    mCheckbox1.setButtonDrawable(R.drawable.checkbo_check);
                } else {
                    MainActivity.checkBox1 = false;
                    mCheckbox1.setButtonDrawable(R.drawable.checkbox_uncheck);
                }
            }
        });
        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckbox2.isChecked()) {
                    MainActivity.checkBox2 = true;
                    mCheckbox2.setButtonDrawable(R.drawable.checkbo_check);
                } else {
                    MainActivity.checkBox2 = false;
                    mCheckbox2.setButtonDrawable(R.drawable.checkbox_uncheck);
                }
            }
        });
        mCheckbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckbox3.isChecked()) {
                    MainActivity.checkBox3 = true;
                    mCheckbox3.setButtonDrawable(R.drawable.checkbo_check);
                } else {
                    MainActivity.checkBox3 = false;
                    mCheckbox3.setButtonDrawable(R.drawable.checkbox_uncheck);
                }
            }
        });

        mBrowseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDialog();
            }
        });
        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!encodedString.equals("")){
                    if (Helpers.isNetworkAvailable(getActivity())){
                        mProgressDialog = new ProgressDialog(getActivity());
                        mProgressDialog.setMessage("Wait Image sending...");
                        mProgressDialog.setIndeterminate(false);
                        mProgressDialog.setCancelable(true);

                        ApiRequest apiRequest = new ApiRequest();
                        Map map = new HashMap();
                        map.put("image",encodedString);
                        apiRequest.setMap(map);
                        ApiCallServices.action(getActivity(), Cv.ACTION_IMAGE_SEND);
                        mProgressDialog.show();
                    }else {
                        Snackbar snackbar1 = Snackbar.make(mConstraintLayout, "Please Check your internet connection...!!!", Snackbar.LENGTH_SHORT);
                    }

                }else {
                    Toast.makeText(getActivity(), "Please select Image", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private void startDialog() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(getActivity());
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setNegativeButton("Gallary",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        getIntent.setType("image/*");
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickIntent.setType("image/*");
                        startActivityForResult(pickIntent, Cv.REQUEST_GALLERY);
                    }
                });
        myAlertDialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            photo = null;
            switch (requestCode) {
                case Cv.REQUEST_GALLERY:
                    try {
                        imageToUploadUri = data.getData();
                        //imagePath = data.getData().getPath();
                        photo = ImagePicker.getImageFromResult(getActivity(), resultCode, data);
                        if (photo != null) {
                            mCheckbox.setVisibility(View.VISIBLE);
                            mCheckbox1.setVisibility(View.VISIBLE);
                            mImage.setVisibility(View.VISIBLE);
                            encodedString = Helpers.bitmapToBase64(photo);
                            mImage.setImageBitmap(photo);
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
