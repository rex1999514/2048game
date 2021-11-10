package com.company;



//processing class where i actually perform the calculation
public class numbers {
    //the 2-d numbers store all my data
    node data[][];
    int size;

    boolean add;
    //goal
    int max=2048;

    //initialize my data to be the correct size
    public numbers(int size)
    {
        data=new node[size][size];
        this.size=size;
        add=false;
    }


    //return a single number
    public int at(int i, int j)
    {
        return data[i][j].getValue();
    }

    //set the goal number
    public void setMax(int m)
    {
        max*=m;
    }

    // create two "2" at two random positions
    public void setNumbers()
    {
        int one=0,two=0;
        while (one == two)
        {
            one = ((int)(Math.random() * 100) )%(size*size);
            two = ((int)(Math.random() * 100) )%(size*size);
        }

        for(int i=0;i<size;++i)
        {
            for(int j=0;j<size;++j)
            {
                data[i][j]=new node();
                data[i][j].setValue(0);
            }
        }
        data[one/size][one%size].setValue(2);
        data[two/size][two%size].setValue(2);

    }





    //check merge up and move up
    public void up()
    {
        add=false;

        for(int m=0;m<size;++m)
        {
            for(int i=0;i<size-1;++i)
            {
                for (int j = i + 1; j < size; ++j) {
                    //check if i can merge
                    if( CheckMerge(data[i][m],data[j][m]))
                    {
                        merge(data[i][m],data[j][m],2);
                        add=true;
                        break;
                    }
                }
            }

        }
        for(int i=0;i<size;++i)
        {
            for (int j=0;j<size;++j)
            {
                if(data[j][i].getValue()!=0)
                {
                    for(int m=0;m<size;++m)
                    {//check there is empty space for it to move up
                        if(data[m][i].getValue()==0&&m<j)
                        {
                            merge(data[m][i],data[j][i],1);
                            add=true;
                        }
                    }
                }
            }
        }

        //if merged or moved then generate a "2" at random position
        if(add)
        {
            addNode(generateNum(),2);
        }
    }
    //check merge down and move down
    public void down()
    {
        add=false;
        for(int m=0;m<size;++m)
        {
            for(int i=size-1;i>0;--i)
            {
                for (int j = i - 1; j >=0; --j) {
                    if(CheckMerge(data[i][m],data[j][m]))
                    {
                        merge(data[i][m],data[j][m],2);
                        add=true;
                        break;

                    }
                }
            }
        }
        for(int i=0;i<size;++i)
        {
            for (int j=size-1;j>=0;--j)
            {
                if(data[j][i].getValue()!=0)
                {
                    for(int m=size-1;m>=0;--m)
                    {
                        if(data[m][i].getValue()==0&&m>j)
                        {
                            merge(data[m][i],data[j][i],1);
                            add=true;

                        }
                    }
                }
            }
        }
        if(add)
        {
            addNode(generateNum(),2);
        }
    }

    //check if merge left or move left
    public void left()
    {
        boolean add=false;
        for(int m=0;m<size;++m)
        {
            for(int i=0;i<size-1;++i)
            {
                for (int j = i + 1; j < size; ++j) {

                    if(CheckMerge(data[m][i],data[m][j]))
                    {
                        merge(data[m][i],data[m][j],2);
                        add=true;
                        break;

                    }
                }
            }

        }
        for(int i=0;i<size;++i)
        {
            for (int j=0;j<size;++j)
            {
                if(data[i][j].getValue()!=0)
                {
                    for(int m=0;m<size;++m)
                    {
                        if(data[i][m].getValue()==0&&m<j)
                        {
                            merge(data[i][m],data[i][j],1);
                            add=true;
                        }
                    }
                }
            }
        }
        if(add)
        {
            addNode(generateNum(),2);
        }
    }
    //check merge right and move right
    public void right()
    {
        add=false;
        for(int m=0;m<size;++m)
        {
            for(int i=size-1;i>0;--i)
            {
                for (int j = i -1; j >=0; --j)
                {
                    if(CheckMerge(data[m][i],data[m][j]))
                    {
                        merge(data[m][i],data[m][j],2);
                        add=true;
                        break;

                    }
                }
            }

        }

        for(int i=0;i<size;++i)
        {
            for (int j=size-1;j>=0;--j)
            {
                if(data[i][j].getValue()!=0)
                {
                    for(int m=size-1;m>=0;--m)
                    {
                        if(data[i][m].getValue()==0&&m>j)
                        {
                            merge(data[i][m],data[i][j],1);
                            add=true;

                        }
                    }
                }
            }
        }
        if(add)
        {
            addNode(generateNum(),2);
        }
    }

    //double the value of first number and set the second number to zero
    public void merge(node first,node second, int t)
    {
        first.setValue(t*second.getValue());
        second.setValue(0);
    }


    //check if those two number are the same
    public boolean CheckMerge(node first,node second)
    {
        return(first.getValue()==second.getValue() && first.getValue()!=0);
    }

    //generate a "2" at the position
    public void addNode(int pos,int num)
    {
        if(pos!=-1)
        {
            data[pos/size][pos%size].setValue(num);
        }
    }

    //check if every blocks are filled and cannot merge with each other anymore
    public boolean checkFull()
    {
        for(int i=0;i<size;++i)
        {
            for(int j=0;j<size;++j)
            {
                if(data[i][j].getValue()==0)
                {
                    return false;
                }
            }
        }
        return true;
    }


    //if the board is not full then create a "2" at a random position
    public int generateNum()
    {
        if(checkFull())
        {
            return -1;
        }
        int pos=((int)(Math.random() * 100) )%(size*size);
        while (data[pos/size][pos%size].getValue()!=0)
        {
            pos= ((int)(Math.random() * 100) )%(size*size);
        }
        return pos;
    }



    //check if there is a number reach the maximum number we set
    public boolean win()
    {
        for(int i=0;i<size;++i)
        {
            for(int j=0;j<size;++j)
            {
                if(data[i][j].getValue()==max)
                {
                    return true;
                }
            }
        }
        return false;

    }
    //if cannot merge or move anymore then you lose
    public boolean lose()
    {
        for(int i=0;i<size;++i)
        {
            for(int j=0;j<size-1;++j)
            {
                if(data[i][j].getValue()==data[i][j+1].getValue())
                {
                    return false;
                }
                if(data[j][i].getValue()==data[j+1][i].getValue())
                {
                    return false;
                }
                if(data[i][j].getValue()==0||data[i][j+1].getValue()==0)
                {
                    return false;
                }
            }
        }
        return true;
    }


}
