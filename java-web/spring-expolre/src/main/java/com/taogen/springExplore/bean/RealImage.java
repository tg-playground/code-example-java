package com.taogen.springExplore.bean;

public class RealImage implements Image
{

    private String filename;

    public RealImage(){}

    /**
     *
     * @param filename file name
     */
    public RealImage(String filename)
    {
        this.filename = filename;
    }

    /**
     * display implementation.
     */
    @Override
    public void display()
    {
        System.out.println(this.filename + " display...");
    }
}
