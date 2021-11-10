package com.company;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//the gui class
public class gui {
    int size,totalSize;
    int x_pos,y_pos;
    Rectangle board[];
    Text text_number[];
    Rectangle backGround,menubackGround;
    public Button easy,median,hard;
    Text score = new Text(800, 700, "SCORE: ");

    public gui()
    {
        //default size to be 4 x 4
        //20 distance between each blocks
        size=4;
        totalSize=size*size;
        x_pos=20;y_pos=20;
    }

    //for level median and hard
    //different size; 5 x 5 or 6x6
    public void setSize(int s)
    {
        size =s;
        totalSize=size*size;
        board= new Rectangle[totalSize];
        text_number=new Text[totalSize];
    }

    //return text
    public Text[] getText_number()
    {
        return text_number;
    }
    //return the play board
    public Rectangle[] getBoard()
    {
        return board;

    }
    public Rectangle getBackGround()
    {
        return backGround;
    }
    public Rectangle getMenubackGround()
    {
        return menubackGround;
    }
    public Button getEasy()
    {
        return easy;
    }
    public Button getMedian()
    {
        return median;
    }
    public Button getHard()
    {
        return hard;
    }

    //set the easy, median, hard button

    public void setMenuButton()
    {
        menubackGround = new Rectangle(500,600);
        menubackGround.setFill(getColor(0));
        easy= new Button("   Easy MODE  ");
        easy.setMinSize(200,100);
        easy.setStyle("-fx-background-color: #fff89b; -fx-font-size: 3em;");
        median = new Button("Median MODE");
        median.setMinSize(200,100);
        median.setStyle("-fx-background-color: #ffc186; -fx-font-size: 3em;");
        hard = new Button("   Hard MODE  ");
        hard.setMinSize(200,100);
        hard.setStyle("-fx-background-color: #ff522c; -fx-font-size: 3em;");
        easy.setLayoutX(120);
        easy.setLayoutY(100);
        median.setLayoutY(250);
        median.setLayoutX(120);
        hard.setLayoutY(400);
        hard.setLayoutX(120);
    }
    //change the text number by looking at the actually numbers
    public void modifyText(numbers data)
    {
        for(int i=0;i<size;++i)
        {
            for (int j=0;j<size;++j)
            {
                if(data.at(i,j)==0)
                {
                    text_number[size*i+j].setText("");
                    board[size*i+j].setFill(getColor(0));
                }
                else
                {
                    text_number[size*i+j].setText(String.valueOf(data.at(i,j)));
                    board[size*i+j].setFill(getColor((data.at(i,j))));
                }
            }
        }
    }
    //set up the play board background
    public void setBackGround()
    {
        backGround =new Rectangle(0,0,950,800);
        backGround.setArcWidth(30);
        backGround.setArcHeight(30);
        backGround.setFill(Color.rgb(187, 173, 160) );
    }
    //first time fill up the board with text numbers and fill each blocks with colors
    public void fillBoard(numbers data)
    {
        for (int i=1;i<=totalSize;++i)
        {

            board[i-1]=new Rectangle(x_pos,y_pos,100,100);
            board[i-1].setArcHeight(20);
            board[i-1].setArcWidth(20);
            board[i-1].setFill((getColor(0)));
            if(data.at((i-1)/size,(i-1)%size)!=0)
            {
                text_number[i-1]=new Text(x_pos+5,y_pos+10,String.valueOf(data.at((i-1)/size,(i-1)%size)));
                board[i-1].setFill(getColor(2));
            }
            else
                text_number[i-1]=new Text(x_pos+20,y_pos+20,"");
            text_number[(i-1)].setFont(Font.font("SansSerif",  30));

            x_pos+=120;
            if(i%size==0)
            {
                x_pos=20;
                y_pos += 120;
            }
        }
    }


    //return the color i need
    public Color getColor(int value)
    {
        switch (value) {
            case 2:    return Color.rgb(238,233,226);
            case 4:    return Color.rgb(237,224,200);
            case 8:    return Color.rgb(242,177,121);
            case 16:   return Color.rgb(245,149,99);
            case 32:   return Color.rgb(246,124,95);
            case 64:   return Color.rgb(246,94,59);
            case 128:  return Color.rgb(237,207,114);
            case 256:  return Color.rgb(237,204,97);
            case 512:  return Color.rgb(237,199,80);
            case 1024: return Color.rgb(237,197,63);
            case 2048: return Color.rgb(237,194,46);
            case 4096: return Color.rgb(237,174,35);
            case 8192: return Color.rgb(237,119,35);

        }
        return Color.rgb(205,193,180);
    }
}
