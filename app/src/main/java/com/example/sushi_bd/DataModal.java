package com.example.sushi_bd;

public class DataModal {
    private String Image;
    private String Name;
    private String Compound;
    private int Price;
    public  DataModal(String Image,String Name,String Compound,int Price){

        this.Image=Image;
        this.Name=Name;
        this.Compound=Compound;
        this.Price=Price;
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

