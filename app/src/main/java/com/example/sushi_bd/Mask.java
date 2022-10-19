package com.example.sushi_bd;

import android.os.Parcel;
import android.os.Parcelable;

public class Mask implements Parcelable {
    private int Id;
    private String Image;
    private String Name;
    private String Compound;
    private int Price;
    public  Mask(int Id,String Image,String Name,String Compound,int Price){
        this.Id=Id;
        this.Image=Image;
        this.Name=Name;
        this.Compound=Compound;
        this.Price=Price;
    }
    protected Mask(Parcel in) {
        Id=in.readInt();
        Image=in.readString();
        Name=in.readString();
        Compound=in.readString();
        Price=in.readInt();
    }

    public static final Creator<Mask> CREATOR = new Creator<Mask>() {
        @Override
        public Mask createFromParcel(Parcel in) {
            return new Mask(in);
        }

        @Override
        public Mask[] newArray(int size) {
            return new Mask[size];
        }
    };

    public void setId(int Id)
    {
        this.Id=Id;
    }
    public void setImage(String Image)
    {
        this.Image=Image;
    }
    public void setName(String Name ){this.Name=Name;}
    public void setCompound (String Compound)
    {
        this.Compound=Compound;
    }
    public void setPrice (int Price)
    {
        this.Price=Price;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(Image);
        parcel.writeString(Name);
        parcel.writeString(Compound);
        parcel.writeInt(Price);
    }

    public int getId()
    {
        return Id;
    }
    public String getImage()
    {
        return Image;
    }
    public String getName()
    {
        return Name;
    }
    public String getCompound()
    {
        return Compound;
    }
    public int getPrice()
    {
        return Price;
    }

}
