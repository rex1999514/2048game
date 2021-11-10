package com.company;

public class node {
    //represents each single blocks
    int value;
    boolean moved;
    boolean died;
    public node()
    {
        value=0;
        moved=false;
        died=false;
    }

    public int getValue()
    {
        return value;
    }
    public void setValue(int v )
    {
        value=v;
    }

}
