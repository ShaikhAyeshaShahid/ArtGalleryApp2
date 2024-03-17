package com.example.artgalleryapp.Product;

public class ProductModule
{
    private String headline, discription, price, brand, productType, aboutProduct, origin, pid;

    private String category;

    private String productImage;

    public ProductModule ( )
    {

    }

    public ProductModule (String headline, String discription, String price, String brand, String productType, String aboutProduct, String origin, String productImage, String category, String pid)
    {
        this.headline = headline;
        this.discription = discription;
        this.price = price;
        this.brand = brand;
        this.productType = productType;
        this.aboutProduct = aboutProduct;
        this.origin = origin;
        this.productImage = productImage;
        this.category = category;
        this.pid = pid;
    }

    public String getHeadline ( )
    {
        return headline;
    }

    public void setHeadline (String headline)
    {
        this.headline = headline;
    }

    public String getDiscription ( )
    {
        return discription;
    }

    public void setDiscription (String discription)
    {
        this.discription = discription;
    }

    public String getPrice ( )
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getBrand ( )
    {
        return brand;
    }

    public void setBrand (String brand)
    {
        this.brand = brand;
    }

    public String getProductType ( )
    {
        return productType;
    }

    public void setProductType (String productType)
    {
        this.productType = productType;
    }

    public String getAboutProduct ( )
    {
        return aboutProduct;
    }

    public void setAboutProduct (String aboutProduct)
    {
        this.aboutProduct = aboutProduct;
    }

    public String getOrigin ( )
    {
        return origin;
    }

    public void setOrigin (String origin)
    {
        this.origin = origin;
    }

    public String getProductImage ( )
    {
        return productImage;
    }

    public void setProductImage (String productImage)
    {
        this.productImage = productImage;
    }

    public String getCategory ( )
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getPid ( )
    {
        return pid;
    }

    public void setPid (String pid)
    {
        this.pid = pid;
    }
}
