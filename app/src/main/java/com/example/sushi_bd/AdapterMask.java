package com.example.sushi_bd;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class AdapterMask extends BaseAdapter {
    private Context nContext;
    List<Mask> maskList;

    public AdapterMask(Context nContext, List<Mask> listSushi)
    {
        this.nContext=nContext;
        this.maskList=listSushi;
    }

    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int i) {
        return maskList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return maskList.get(i).getId();
    }

    public static Bitmap loadContactPhoto(ContentResolver cr, long id, Context context) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
        if (input == null) {
            Resources res = context.getResources();
            return BitmapFactory.decodeResource(res, R.drawable.zaglushka);
        }
        return BitmapFactory.decodeStream(input);
    }


    private Bitmap getUserImage(String encodedImg)
    {
        byte[] bytes;
        if(encodedImg!=null&& !encodedImg.equals("null")) {
            bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else
        {

            return BitmapFactory.decodeResource(nContext.getResources(), R.drawable.zaglushka);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(nContext,R.layout.mask_sushi, null);
        ImageView Image=v.findViewById(R.id.shapeableImageView);
        TextView Name=v.findViewById(R.id.Name);
        TextView Compound=v.findViewById(R.id.Compound);
        TextView Price=v.findViewById(R.id.Price);

        Mask mask=maskList.get(i);
        Name.setText(mask.getName());
        Compound.setText(mask.getCompound());
        Price.setText(Integer.toString(mask.getPrice()));
        Image.setImageBitmap(getUserImage(mask.getImage()));
        /*
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenDetalis=new Intent(nContext,Update_Delet.class);
                intenDetalis.putExtra("Sushi",mask);
                nContext.startActivity(intenDetalis);

            }
        });
        */

        return v;
    }
}
