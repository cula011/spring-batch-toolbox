package org.cula011.spring.batch.domain;

public class Address
{
    private int streetNumber;
    private String streetName;
    private String streetType;
    private String suburb;
    private String city;
    private String state;
    private int postcode;
    private String country;

    public int getStreetNumber()
    {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    public String getStreetName()
    {
        return streetName;
    }

    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    public String getStreetType()
    {
        return streetType;
    }

    public void setStreetType(String streetType)
    {
        this.streetType = streetType;
    }

    public String getSuburb()
    {
        return suburb;
    }

    public void setSuburb(String suburb)
    {
        this.suburb = suburb;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public int getPostcode()
    {
        return postcode;
    }

    public void setPostcode(int postcode)
    {
        this.postcode = postcode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}