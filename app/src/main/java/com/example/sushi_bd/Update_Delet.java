package com.example.sushi_bd;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Update_Delet extends AppCompatActivity {
    ImageView imageView;
    EditText Name, Price, Compound;
    String img="";
    Mask mask;

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delet);

        mask=getIntent().getParcelableExtra("Sushi");
        imageView=findViewById(R.id.UpdateImage);

        Name=findViewById(R.id.UpName);
        Name.setText(mask.getName());
        Price=findViewById(R.id.UpPrice);
        Price.setText(Integer.toString(mask.getPrice()));
        Compound=findViewById(R.id.UpCompound);
        Compound.setText(mask.getCompound());
        imageView.setImageBitmap(getImgBitmap(mask.getImage()));

    }

    private Bitmap getImgBitmap(String encodedImg) {
        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getDecoder().decode(encodedImg);
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else{
            return BitmapFactory.decodeResource(Update_Delet.this.getResources(),
                    R.drawable.zaglushka);
        }

    }


    public void onClickChooseImage(View view)
    {
        getImage();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && data!= null && data.getData()!= null)
        {
            if(resultCode==RESULT_OK)
            {
                Log.d("MyLog","Image URI : "+data.getData());
                imageView.setImageURI(data.getData());
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                encodeImage(bitmap);

            }
        }
    }

    private void getImage()
    {
        Intent intentChooser= new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser,1);
    }

    private String encodeImage(Bitmap bitmap) {
        int prevW = 150;
        int prevH = bitmap.getHeight() * prevW / bitmap.getWidth();
        Bitmap b = Bitmap.createScaledBitmap(bitmap, prevW, prevH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            img=Base64.getEncoder().encodeToString(bytes);
            return img;
        }
        return "";
    }
    public void Update_bt(View v)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Update_Delet.this);
        builder.setTitle("Изменение")
                .setMessage("Вы уверены что хотите изменить данные")
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        posUpdate(img,Name.getText().toString(),Compound.getText().toString(),Price.getText().toString());
                        Next();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void posUpdate(String image, String  name ,String compound,String price)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5101/ngknn/ТрифоноваАР/api/Sushis/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPIUpdate update = retrofit.create(RetrofitAPIUpdate.class);
        DataModal modal = new DataModal(image, name,compound,Integer.parseInt(price));
        Call<DataModal> call = update.updateData(modal);
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                Toast.makeText(Update_Delet.this, "Запись изменена", Toast.LENGTH_SHORT).show();
                DataModal responseFromAPI = response.body();
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {

            }
        });
    }

    public void Delet_bt(View v)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(Update_Delet.this);
        builder.setTitle("Удалить")
                .setMessage("Вы уверены что хотите Удалить данные")
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Next();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void Next()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}