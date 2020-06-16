
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton; 


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.Random;

public class GUI extends XOButton {
    private static final long serialVersionUID = 1L;

    JFrame frame = new JFrame(); 

	XOButton buttons[]=new XOButton[9];


    JPanel totalLeftPanel = new JPanel(); 
    JPanel totalRightPanel = new JPanel(); 

    JPanel rightPanel = new JPanel(); 


    JButton button = new JButton("Enter");
    JLabel label = new JLabel("Enter your Name"); 
    JTextField text = new JTextField(10); 

    
    private String input = ""; 

    private String first = ""; 
    private String second = "";
    private String human = "";

    private int counter; 

    static String[][] board = {
        {" ","0", "1", "2"}, 
        {"0","-", "-", "-"},
        {"1","-", "-", "-"}, 
        {"2","-", "-", "-"}};




    public GUI()
    {
        
    
        frame.setTitle("TicTacToe"); 
        frame.setSize(800,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0,2));
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.RED)); 
 

        totalRightPanel.setLayout(new GridLayout(0,1)); 
        rightPanel.setBackground(Color.BLUE); 

        rightPanel.add(label); 
        rightPanel.add(text);
        rightPanel.add(button); 


                
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    if(e.getSource() == button)
                    {
                        input = text.getText(); 
                        
                        String[] names = start(input); 
    
                        first = names[0]; 
                        second = names[1]; 
                        human = names[2]; 
    
            
                        label.setVisible(false); 
                        text.setVisible(false); 
                        button.setVisible(false); 
    
                        JLabel versus = new JLabel("AI-19  VERSUS " + human); 
                        versus.setFont(new Font("Onyx", Font.ITALIC, 20));
                
                        JLabel intro = new JLabel (first + " is going first! and " + second + " is going  second!"); 
                        intro.setFont(new Font("Onyx", Font.BOLD, 15));
    
                        rightPanel.add(versus); 
                        rightPanel.add(intro); 

                        if(human.equals(second))
                        {
        
                            for(int y = 0; y < 9; y++)
                            {
                                System.out.println(a); 
                                System.out.println(); 
                                int position = getPosition(offense(a)); 
                                buttons[position].setX();
                                buttons[position].setEnabled(false);  
                                setBoard(position, "X"); 
                                AIWin(); 
                                checkFinalTie(); 
                                printBoard();


                                final Integer inner = Integer.valueOf(y);
                                buttons[inner].addActionListener(new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent e)
                                    {  
                                        
                                         
                                        // human goes second 
                                            if(getFinalTie())
                                            {
                                                checkFinalTie(); 
                                            } 

                                            buttons[inner].setO(); 
                                            buttons[inner].setEnabled(false);    
                                            setBoard(inner, "O"); 

                                            humanWin(); 
                                            checkFinalTie(); 
                                            addA(); 
                                            printBoard();

                                            System.out.println(a); 
                                            System.out.println(); 
                                            int position = getPosition(defense(a));
                                            buttons[position].setX(); 
                                            buttons[position].setEnabled(false);
                                            setBoard(position, "X");

                                            if(getFinalTie())
                                            {
                                                checkFinalTie(); 
                                            } 
                                            AIWin();
                                            checkFinalTie(); 
                                            printBoard();


                                        
                                    }
                                });
                            } 
                        }
                    }
    
                }
            }); 
    
    
            totalLeftPanel.setLayout(new GridLayout(3,3)); 
            for(int i = 0; i < 9; i++)
            {
                buttons[i] = new XOButton(); 
                totalLeftPanel.add(buttons[i]); 
            }


            for(int x = 0; x < 9; x++)
            {
                final Integer innerI = Integer.valueOf(x); 

                buttons[innerI].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        if(human.equals(first))
                        {
                            // human goes first 
                                buttons[innerI].setX();
                                buttons[innerI].setEnabled(false);    

                                setBoard(innerI, "X"); 

                                if(checkWinX() || checkWinO())
                                {
                                    humanWin(); 
                                }
                                checkFinalTie();
                                addA(); 
                                printBoard();
                                

                                if(!checkWinX() || !checkWinO())
                                {
                                    int position = getPosition(defense(a));
                                    buttons[position].setO(); 
                                    buttons[position].setEnabled(false);
                                    setBoard(position, "O");
    
                                    AIWin();
                                    if(getFinalTie())
                                    {
                                        checkFinalTie(); 
                                    }
                                    printBoard();
                                }
                        }
                        
                    
                    }
                });
            }

        totalRightPanel.add(rightPanel); 


        // add the person who goes first and second 

        frame.add(totalLeftPanel); 
        frame.add(totalRightPanel); 

        //play(first, second, human); 
        frame.setVisible(true);    // the frame is set up correctly and the tic tac toe board 
    }

    public void humanWin()
    {
        if(checkWinX() || checkWinO())
        {
            JPanel winnerHuman = new JPanel(); 
            winnerHuman.setBackground(Color.BLUE);
            JLabel humanLabel = new JLabel(); 

            humanLabel.setText("Congratulations " + human + "... you WON!"); 
            humanLabel.setFont(new Font("Courier New", Font.ITALIC, 30));
            winnerHuman.add(humanLabel); 

            frame.remove(totalLeftPanel);
            frame.remove(totalRightPanel);
            frame.setLayout(new GridLayout(0,1));
            frame.add(winnerHuman); 
        }
    }
    public void AIWin()
    {
        if(checkWinX() || checkWinO())
        {
            JPanel winnerAI = new JPanel(); 
            winnerAI.setBackground(Color.RED); 
            JLabel AILabel = new JLabel(); 

            AILabel.setText("Sorry " + human + "... you LOST to AI-19!!");
            AILabel.setFont(new Font("Courier New", Font.ITALIC, 30));
            winnerAI.add(AILabel); 

            frame.remove(totalLeftPanel);
            frame.remove(totalRightPanel);
            frame.setLayout(new GridLayout(0,1));
            frame.add(winnerAI); 
        }
    }
        
    public void checkFinalTie()
    {
        if(getFinalTie())
        {
            JPanel tie = new JPanel(); 
            tie.setBackground(Color.WHITE);
            JLabel tieLabel = new JLabel(); 
    
            tieLabel.setText("There was a TIE!"); 
            tieLabel.setFont(new Font("Courier New", Font.ITALIC, 30));
            tie.add(tieLabel);
    
            frame.remove(totalLeftPanel);
            frame.remove(totalRightPanel);
            frame.setLayout(new GridLayout(0,1));
            frame.add(tie); 
        }
        
    }
            
    
    
    public String[] start(String input)
    {
        String[] s1 = new String[3]; 
        Random random = new Random(); // random 
        int rand = random.nextInt(2);  

        String first = ""; 
        String second = ""; 
        if(rand == 1)
        {
            first = "AI-19"; 
            second = input; 
        }
        else 
        {
            first = input; 
            second = "AI-19";  
        }
            
        String human = ""; 

        if(first.equals("AI-19"))
        {
            human = second; 
        }
        else
        {
            human = first; 
        }
        //first,second,human
    

        s1[0] = first; 
        s1[1] = second;
        s1[2] = human;

        return s1;         
   }






   public int getPosition(int[] coordinates)
   {
        int row = coordinates[0]; 
        int col = coordinates[1]; 
        if(row == 0 && col == 0)
        {
            return 0;
        }
        if(row == 0 && col == 1)
        {
            return 1;
        }
        if(row == 0 && col == 2)
        {
            return 2;
        }
        if(row == 1 && col == 0)
        {
            return 3;
        }
        if(row == 1 && col == 1)
        {
            return 4;
        }
        if(row == 1 && col == 2)
        {
            return 5;
        }
        if(row == 2 && col == 0)
        {
            return 6;
        }
        if(row == 2 && col == 1)
        {
            return 7;
        }
        if(row == 2 && col == 2)
        {
            return 8;
        }

        return -1; 
   }

   public void setBoard(int innerI, String letter)
   {
        if(innerI == 0)
        {
            board[1][1] = letter; 
        }
        if(innerI == 1)
        {
            board[1][2] = letter; 
        }
        if(innerI == 2)
        {
            board[1][3] = letter; 
        }


        if(innerI == 3)
        {
            board[2][1] = letter; 
        }
        if(innerI == 4)
        {
            board[2][2] = letter; 
        }
        if(innerI == 5)
        {
            board[2][3] = letter; 
        }
        
        
        if(innerI == 6)
        {
            board[3][1] = letter; 
        }
        if(innerI == 7)
        {
            board[3][2] = letter; 
        }
        if(innerI == 8)
        {
            board[3][3] = letter; 
        }

   }


   public boolean getTie()
   {
       for(int x = 0; x < 9; x++)
       {
           final Integer innerI = Integer.valueOf(x); 

           buttons[innerI].addActionListener(new ActionListener()
           {   
               public void actionPerformed(ActionEvent e)
               {
                   counter = 0; 
                   for(int i = 0; i < 9; i++)
                   {
                       if(buttons[i].hasX() == true || buttons[i].hasO() == true)
                       {
                           counter++;
                       }
                   }
   
               }
               
           });
           
       }
       if(counter == 8)
       {
           return true; 
       }
       return false; 
   }

   public boolean checkRowX()
   {
        if((buttons[0].hasX() && buttons[1].hasX() && buttons[2].hasX()) ||(buttons[3].hasX() && buttons[4].hasX() && buttons[5].hasX())||(buttons[6].hasX() && buttons[7].hasX() && buttons[8].hasX()))
        {
           return true; 
        }
        return false;         
   }

   public boolean checkColX()
   {
        if((buttons[0].hasX() && buttons[3].hasX() && buttons[6].hasX()) || (buttons[1].hasX() && buttons[4].hasX() && buttons[7].hasX())||(buttons[2].hasX() && buttons[5].hasX() && buttons[8].hasX()))
        {
            return true; 
        }
        return false; 
   }
   public boolean checkDiagX()
   {
        if((buttons[0].hasX() && buttons[4].hasX() && buttons[8].hasX()) || (buttons[6].hasX() && buttons[4].hasX() && buttons[2].hasX()))
        {
            return true; 
        }
        return false; 
   }

   public boolean checkWinX()
   {
       if(checkDiagX() || checkColX() || checkRowX())
       {
           return true;
       }
       return false; 
   }

   public boolean checkRowO()
   {
        if((buttons[0].hasO() && buttons[1].hasO() && buttons[2].hasO()) ||(buttons[3].hasO() && buttons[4].hasO() && buttons[5].hasO())||(buttons[6].hasO() && buttons[7].hasO() && buttons[8].hasO()))
        {
           return true; 
        }
        return false;         
   }

   public boolean checkColO()
   {
        if((buttons[0].hasO() && buttons[3].hasO() && buttons[6].hasO()) || (buttons[1].hasO() && buttons[4].hasO() && buttons[7].hasO())||(buttons[2].hasO() && buttons[5].hasO() && buttons[8].hasO()))
        {
            return true; 
        }
        return false; 
   }
   public boolean checkDiagO()
   {
        if((buttons[0].hasO() && buttons[4].hasO() && buttons[8].hasO()) || (buttons[6].hasO() && buttons[4].hasO() && buttons[2].hasO()))
        {
            return true; 
        }
        return false; 
   }

   public boolean checkWinO()
   {
       if(checkDiagO() || checkColO() || checkRowO())
       {
           return true;
       }
       return false; 
   }

   public boolean getFinalTie()
   {
       if((getTie()) && !checkWinX() && !checkWinO())
       {
           return true;
       }
       else
       {
           return false;
       }
   }


   public void printBoard()
   {
       for(int x = 1; x < 4; x++)
       {
           for(int y = 1; y < 4; y++)
           {
               System.out.print(board[x][y] + " "); 
           }
           System.out.println(); 
       }
   }





    static int [] coordinate  = {0,0}; 
    static int [] incorrect  = {0,0}; 
    static int [] lastSpot = {0,0};






 
    public static int[] offense(int turn)
    {
      switch(turn)
        {
            case 0:
                return setOne();  //avaliable(String letter, int row, int col)
             
            case 2: 

                if( (available("X",0,0) && available("O",0,1)) || // second 
                    (available("X",0,0) && available("O",1,0)) || // fourth
                    (available("X",0,0) && available("O",2,0)) || // seven
                    (available("X",0,0) && available("O",2,1)) || // eight
                    (available("X",0,0) && available("O",2,2)))   // ninth
                {
                    return setThree(); 
                
                }
                if( (available("X",0,0) && available("O",0,2)) || // three
                    (available("X",0,0) && available("O",1,2)))   // six 
                {
                    return setSeven(); 
                  
                }
                if((available("X",0,0) && available("O",1,1)))   // five 
                {
                    return setNine(); 
                }
                
            case 4: 
            
                if(finishTester("X"))
                {
                    return finish("X");
                }
                if(finishTester("O"))
                {
                    return block(); 
                }
                
                // A 
        
                if( (available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",1,0)) || 
                    (available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",2,0)))
                    {
                        return setNine(); // SETUP A.1 and A.4
                         
                    }
                if( (available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",1,2)) || 
                    (available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",2,2)))
                    {
                        return setSeven(); // SETUP A.3 and A.6 
                      
                    }
                    
                if((available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",1,1)))
                {
                    return setEight(); // A.2 TIE 
                    
                }
                if((available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",2,1)))
                {
                    return setFive(); // SETUP A.5
                    
                }
                
                // B 
                
                if( (available("X",0,0) && available("O",0,2) && available("X",2,0) && available("O",0,1)) ||
                    (available("X",0,0) && available("O",0,2) && available("X",2,0) && available("O",1,1)) ||
                    (available("X",0,0) && available("O",0,2) && available("X",2,0) && available("O",1,2)) ||
                    (available("X",0,0) && available("O",0,2) && available("X",2,0) && available("O",2,1)) ||
                    (available("X",0,0) && available("O",0,2) && available("X",2,0) && available("O",2,2)))
                {
                    return setFour(); // WIN  B.1, B.3, B.4, B.5, B.6
                   
                }
                if(  (available("X",0,0) && available("O",0,2) && available("X",2,0) && available("O",1,0)))
                {
                    return setNine(); // B.2 SETUP
          
                }
                
                // C 
                if( (available("X",0,0) && available("O",1,0) && available("X",0,2) && available("O",1,1)) ||
                    (available("X",0,0) && available("O",1,0) && available("X",0,2) && available("O",1,2)) ||
                    (available("X",0,0) && available("O",1,0) && available("X",0,2) && available("O",2,0)) ||
                    (available("X",0,0) && available("O",1,0) && available("X",0,2) && available("O",2,1)) ||
                    (available("X",0,0) && available("O",1,0) && available("X",0,2) && available("O",2,2)))
                {
                    return setTwo(); // WIN  C.2, C.3, C.4, C. 5, C.6

                }
                if(  (available("X",0,0) && available("O",0,2) && available("X",2,0) && available("O",0,1)))
                {
                    return setNine(); //C.1 SETUP
      
                }
                
                // D 
                if( (available("X",0,0) && available("O",1,1) && available("X",2,2) && available("O",0,2)) || 
                    (available("X",0,0) && available("O",1,1) && available("X",2,2) && available("O",1,2)))
                {
                    return setSeven(); // SETUP D.2, D.4 
                }
                if((available("X",0,0) && available("O",1,1) && available("X",2,2) && available("O",0,1)))
                {
                    return setEight(); // D.1 
                }
                if((available("X",0,0) && available("O",1,1) && available("X",2,2) && available("O",1,0)))
                {
                    return setSix(); // D.3 
                }
                if((available("X",0,0) && available("O",1,1) && available("X",2,2) && available("O",2,0)))
                {
                    return setThree(); // SETUP D.5 
                }
                if((available("X",0,0) && available("O",1,1) && available("X",2,2) && available("O",2,1)))
                {
                    return setTwo(); // D.6
                }
                
                //E
                
                if( (available("X",0,0) && available("O",1,2) && available("X",2,0) && available("O",0,1)) ||
                    (available("X",0,0) && available("O",1,2) && available("X",2,0) && available("O",0,2)) ||
                    (available("X",0,0) && available("O",1,2) && available("X",2,0) && available("O",1,1)) ||
                    (available("X",0,0) && available("O",1,2) && available("X",2,0) && available("O",2,1)) ||
                    (available("X",0,0) && available("O",1,2) && available("X",2,0) && available("O",2,2)))
                {
                    return setFour(); // WIN E.1, E.2, E.4, E.5, E.6 
                }
                if((available("X",0,0) && available("O",1,2) && available("X",2,0) && available("O",1,0)))
                {
                    return setFive(); // SETUP E.3 
                }
                
                // F 
                
                if( (available("X",0,0) && available("O",2,0) && available("X",0,2) && available("O",1,0)) ||
                    (available("X",0,0) && available("O",2,0) && available("X",0,2) && available("O",1,1)) ||
                    (available("X",0,0) && available("O",2,0) && available("X",0,2) && available("O",1,2)) ||
                    (available("X",0,0) && available("O",2,0) && available("X",0,2) && available("O",2,1)) ||
                    (available("X",0,0) && available("O",2,0) && available("X",0,2) && available("O",2,2)))
                {
                    return setFour(); // WIN F.2, F.3, F.4, F.5, F.6  
                }
                if((available("X",0,0) && available("O",2,0) && available("X",0,2) && available("O",0,1)))
                {
                    return setNine(); // SETUP F.1
                }
                
                //G
                if( (available("X",0,0) && available("O",2,1) && available("X",0,2) && available("O",1,0)) ||
                    (available("X",0,0) && available("O",2,1) && available("X",0,2) && available("O",1,1)) ||
                    (available("X",0,0) && available("O",2,1) && available("X",0,2) && available("O",1,2)) ||
                    (available("X",0,0) && available("O",2,1) && available("X",0,2) && available("O",2,0)) ||
                    (available("X",0,0) && available("O",2,1) && available("X",0,2) && available("O",2,2)))
                {
                    return setTwo(); // WIN G.2, G.3, G.4, G.5, G.6 
                }
                if((available("X",0,0) && available("O",2,1) && available("X",0,2) && available("O",0,1)))
                {
                    return setFive(); // SETUP G.1 
                }
                
                //H
                
                if( (available("X",0,0) && available("O",2,2) && available("X",0,2) && available("O",1,0)) ||
                    (available("X",0,0) && available("O",2,2) && available("X",0,2) && available("O",1,1)) ||
                    (available("X",0,0) && available("O",2,2) && available("X",0,2) && available("O",1,2)) ||
                    (available("X",0,0) && available("O",2,2) && available("X",0,2) && available("O",2,0)) ||
                    (available("X",0,0) && available("O",2,2) && available("X",0,2) && available("O",2,1)))
                {
                    return setTwo(); // WIN H.2, H.3, H.4, H.5, H.6  
                }
                if((available("X",0,0) && available("O",2,2) && available("X",0,2) && available("O",0,1)))
                {
                    return setSeven(); // SETUP G.1 
                }
                
            case 6: 
                if(finishTester("X"))
                {
                    return finish("X"); 
                }
                
                //A.2.a
                if((available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",1,1)) && available("X",2,1) && available("O",1,0))
                {
                       return setSix(); // 50/50
                }
                
                //A.2.b 
                if((available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",1,1)) && available("X",2,1) && available("O",1,2))
                {
                       return setFour(); // 50/50
                }
                
                //A.2.c
                if((available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",1,1)) && available("X",2,1) && available("O",2,0))
                {
                       return setNine(); // 50/50
                }
                
                //A.2.d
                if(available("X",0,0) && available("O",0,1) && available("X",0,2) && available("O",1,1) && available("X",2,1) && available("O",2,2))
                {
                       return setSeven(); // 50/50 
                }
                
                
                if(available("X",0,0) && available("O", 1,1) && available("X",2,2) && available("O",0,1) && available("X",2,1))
                {
                    //D.1.a  D.1.b   D.1.c   win 
                    if( available("O",0,2) || available("O",1,0) || available("O",1,2))
                    {
                        return setFour(); 
                    }
                    //D.1.d  50/50
                    if(available("O",2,0))
                    {
                        setThree(); 
                    }
                }
                
                if(available("X",0,0) && available("O",1,1) && available("X",2,2) && available("O",1,0)  && available("X",2,2))
                {
                    if(available("O",0,1))   // D.3a SETUP 
                    {
                        return setEight();  
                    }
                    if(available("O",0,2)) //D.3.b 50/50 
                    {
                        return setSeven();
                    }
                    if(available("O",2,0) && available("O",2,1)) //D.3.c WIN 
                    {
                        return setThree(); //D.3.d WIN /D.3.c WIN 
                    }
                }
                
                if(available("X",0,0) && available("O",1,1) && available("X",2,2) && available("O",2,1) && available("X",0,1))
                {
                    if(available("O",0,2))
                    {
                        return setSeven(); //D.6.a 50/50
                    }
                    if(available("O",1,0) || available("O",1,2) || available("O",2,0))
                    {
                        return setThree(); //D.6.b  D.6.c  D.6.d
                    }
                   
                }
                
            case 8:
                if(finishTester("X"))
                {
                    return finish("X"); 
                }
                return lastSpot(); 
                
                
                
                
        }
        incorrect[0] = 4;
        incorrect[1] = 4; 
        return incorrect; 
    }
    
    
    
    public static int[] defense(int turn)
    {
        switch(turn)
        {
            case 1: 
                if(available("X",0,0) || available("X",2,0) || available("X",0,2) || available("X",2,2))
                {
                    return setFive(); // A. C. G. I. 
                }
                if(available("X",0,1) || available("X",1,0) || available("X",1,1))
                {
                return setNine(); // B. D. E. 
                }
                if(available("X",1,2))
                {
                    return setSeven(); // F. 
                }
                if(available("X",2,1))
                {
                    return setOne(); // H. 
                }
            case 3: 
                
                // A. 
                if(available("X",0,0) && available("O",1,1))
                {
                    if(available("X",0,2) || available("X",2,2))
                    {
                        return setTwo(); 
                    }
                    
                    if(available("X",0,1) || available("X",2,1))
                    {
                        return setThree(); 
                    }
                    
                    if(available("X",1,2) || available("X",1,0))
                    {
                        return setSeven(); 
                    }
                    if(available("X",2,0))
                    {
                        return setFour(); 
                    }
                }
                
                // B. 
                
                if(available("X",0,1) && available("O",2,2))
                {
                    if(available("X",0,2) || available("X",1,2))
                    {
                        return setOne(); 
                    }
                    
                    if(available("X",2,0) || available("X",2,1))
                    {
                        return setFive(); 
                    }
                    
                    if(available("X",0,0))
                    {
                        return setThree();
                    }
                    
                    if(available("X",1,0))
                    {
                        return setSeven(); 
                    }
                    
                    if(available("X",1,1))
                    {
                        return setEight(); 
                    }
                }
                
                // C.
                
                if(available("X",0,2) && available("O",1,1))
                {
                    if(available("X",0,0) || available("X",2,0))
                    {
                        return setTwo();
                    }
                    
                    if(available("X",0,1) || available("X",1,0))
                    {
                        return setOne(); 
                    }
                    
                    if(available("X",1,2) || available("X",2,1))
                    {
                        return setNine(); 
                    }
                    if(available("X",2,2))
                    {
                        return setSix(); 
                    }
                }
                
                // D. 
                
                if(available("X",1,0) && available("O",2,2))
                {
                    if(available("X",0,0) || available("X",1,1))
                    {
                        return setSeven();
                    }
                    
                    if(available("X",0,2) || available("X",2,0))
                    {
                        return setOne(); 
                    }
                    
                    if(available("X",1,1))
                    {
                        return setSix(); 
                    }
                    if(available("X",1,2)) 
                    {
                        return setFive(); 
                    }
                    if(available("X",2,1))
                    {
                        return setThree(); 
                    }
                }
                
                // E. 
                
                if(available("X",1,1) && available("O",2,2))
                {
                    if(available("X",0,0) || available("X",2,0))
                    {
                        return setThree(); 
                    }
                    
                    if(available("X",0,1))
                    {
                        return setEight();
                    }
                    
                    if(available("X",0,2))
                    {
                        return setSeven(); 
                    }
                    
                    if(available("X",1,0))
                    {
                        return setSix(); 
                    }
                    
                    if(available("X",1,2))
                    {
                        return setFour(); 
                    }
                    if(available("X",2,1))
                    {
                        return setTwo();
                    }
                }
                
                // F. 
                
                if(available("X",1,2) && available("O",2,0))
                {
                    if(available("X",0,1) || available("X",0,2))
                    {
                        return setNine();
                    }
                    
                    if(available("X",0,0))
                    {
                        return setTwo();
                    }

                    if(available("X",1,0))
                    {
                        return setFive();
                    }
                    
                    if(available("X",1,1))
                    {
                        return setFour();
                    }
                    
                    if(available("X",1,2))
                    {
                        return setOne();
                    }
                    
                    if(available("X",2,2))
                    {
                        return setThree(); 
                    }
                }
                
                // G. 
                
                if(available("X",2,0) && available("O",1,1))
                {
                    if(available("X",0,1) || available("X",0,2) || available("X",1,0))
                    {
                        return setOne(); 
                    }
                    
                    if(available("X",1,2) || available("X",2,1))
                    {
                        return setNine();
                    }
                    
                    if(available("X",0,0))
                    {
                        return setFour(); 
                    }
                    
                    if(available("X",2,2))
                    {
                        return setEight(); 
                    }
                }
                
                // H. 
                
                if(available("X",2,1) && available("O",0,0))
                {
                    if(available("X",0,1) || available("X",0,2))
                    {
                        return setFive();
                    }
                    
                    if(available("X",1,0) || available("X",2,0))
                    {
                        return setNine();
                    }
                    
                    if(available("X",1,2) || available("X",2,2))
                    {
                        return setSeven();
                    }
                    
                    if(available("X",1,1))
                    {
                        return setTwo();
                    }
                    
                   
                }
                
                // I. 
                if(available("X",2,2) && available("O",1,1))
                {
                    if(available("X",0,1) || available("X",1,0) || available("X",2,1))
                    {
                        return setSeven(); 
                    }
                    
                    if(available("X",0,0))
                    {
                        return setTwo(); 
                    }
                    
                    if(available("X",0,2))
                    {
                        return setSix();
                    }
                    if(available("X",1,2))
                    {
                        return setThree();
                    }
                    if(available("X",2,0))
                    {
                        return setEight(); 
                    }
                }
                
            case 5: 
                if(finishTester("O"))
                {
                    return finish("O");
                }
                
                
                if(finishTester("X"))
                {
                    return block(); 
                }
                
                return desperation(); 
                
            case 7: 
                if(finishTester("O"))
                {
                    return finish("O");
                }
                
                
                if(finishTester("X"))
                {
                    return block(); 
                }
                
                return desperation(); 
                
                
                
                
                
                
                
            
                
        }
        incorrect[0] = 8; 
        incorrect[1] = 8; 
        return incorrect; 
    }

    
       
   public static boolean available(String letter, int row, int col)
    {
        if(letter.equals("X"))
        {
            if(getBoard(row,col).equals("X"))
            {
                return true; 
            }
            else
            {
                return false; 
            }
        }
        if(letter.equals("O"))
        {
            if(getBoard(row,col).equals("O"))
            {
                return true; 
            }
            if(getBoard(row,col).equals("O"))
            {
                return false; 
            }
            
        }
        if(letter.equals("-"))
        {
            if(getBoard(row,col).equals("-"))
            {
                return true; 
            }
            if(getBoard(row,col).equals("-"))
            {
                return false; 
            }
            
        }
        return false; 
    }
    
    public static int[] finish(String letter)
    {
        int [] finish = {0,0};
        int col = 0; 
        int row = 0; 
        int counter = 0; 
        //goes through rows 
        for(int r = 0; r <= 2; r++)
        {
            for(int c = 0; c <=2; c++)
            {
               if(available("-",r,c))
               {
                   row = r; 
                   col = c; 
               }
               if(available(letter,r,c))
               {
                   counter++; 
               }
            }
            
            if(counter == 2 && board[row+1][col+1].equals("-"))
            {
                finish [0] = row; 
                finish [1] = col; 
                return finish; 
            }
            counter = 0; 
            row = 0; 
            col = 0; 
        
            
        }
        // goes throuch columns 
        for(int c = 0; c <= 2; c++)
        {
            for(int r = 0; r <=2; r++)
            {
               if(available("-",r,c))
               {
                   row = r; 
                   col = c; 
               }
               if(available(letter,r,c))
               {
                   counter++; 
               }
            }
            
            if(counter == 2 && board[row+1][col+1].equals("-"))
            {
                finish [0] = row; 
                finish [1] = col; 
                return finish; 
            }
            counter = 0; 
            row = 0; 
            col = 0; 
        }

        // goes through rows 
        for(int r = 0; r <= 2; r++)
        {
            for(int c = 0; c <= 2; c++)
            {
                if( r == 0  && c == 0  ||  r == 1  && c == 1 ||  r == 2  && c == 2)
                {   
                    if(available("-",r,c))
                    {
                        row = r; 
                        col = c; 
                    }
                    if(available(letter,r,c))
                    {
                        counter++; 
                    }
                }

            }
        }
        
        if(counter == 2 && board[row+1][col+1].equals("-"))
        {
            finish[0] = row;
            finish[1] = col; 
            return finish; 
        }
        
        col = 0; 
        row = 0; 
        counter = 0; 
        
        for(int r = 0; r <= 2; r++)
        {
            for(int c = 0; c <= 2; c++)
            {
                if( r == 2  && c == 0  ||  r == 1  && c == 1 ||  r == 0  && c == 2)
                {   
                    if(available("-",r,c))
                    {
                        row = r; 
                        col = c; 
                    }
                    if(available(letter,r,c))
                    {
                        counter++; 
                    }
                }

            }
        }
        
        if(counter == 2 && board[row+1][col+1].equals("-"))
        {
            finish[0] = row;
            finish[1] = col; 
            return finish; 
        }
        finish[0] = 5;
        finish[1] = 5; 
        return finish; 
    }
    
    public static boolean finishTester(String letter)
    {
        int [] finish = finish(letter);
        if(finish[0] == 5 && finish[1] ==5)
        {
            return false;
        }
        return true; 
    } 
    
    public static int [] block()
    {
        int block[];
      
        if(finishTester("X")) // checks if it is true
        {
            block = finish("X");
            // takes the first players coordinates and 
            // returns them to AI so itcan return "O" to 
            // where the first player would have won 
            return block; 
        }
        
        int mistake[] = {12,12}; 
        return mistake; 
    }
    
    
            
    public static int[] desperation() // this is made just for defense  
                               // looks only for "O"  
    {
        int [] coordinate = {0,0};
        
        
        int row1 = 0; 
        int col1 = 0;
        
        int row2 = 0; 
        int col2 = 0; 
        
        int dashes = 0; 
        int letter = 0; 
        
        //goes through rows 
        for(int r = 0; r <= 2; r++)
        {
            for(int c = 0; c <=2; c++)
            {
                if(available("O",r,c))
                {
                   letter++; 
                }
                
                if(available("-",r,c))
                {
                    if(dashes == 0) // sets second coordinates of "-" 
                    {
                        row1 = r; 
                        col1 = c; 
                        dashes++;
                    }
                    if(dashes == 1)
                    {
                        row2 = r; // sets first coordinates of "-"  
                        col2 = c; 
                        dashes++; 
                    }
                }
                
            }
            
       
            if(dashes == 2 && letter == 1 && board[row1+1][col1+1].equals("-") && board[row2+1][col2+1].equals("-")) 
            {
                coordinate [0] = row1; 
                coordinate [1] = col1; 
                return coordinate; 
            }
            row1 = 0; 
            col1 = 0;
        
            row2 = 0; 
            col2 = 0; 
        
            dashes = 0; 
            letter = 0; 
            
        }
        
        // goes throuch columns 
        for(int c = 0; c <= 2; c++)
        {
            for(int r = 0; r <=2; r++)
            {
                if(available("-",r,c))
                {
                   if(dashes == 0) // sets second coordinates of "-" 
                    {
                        row1 = r; 
                        col1 = c; 
                        dashes++;
                    }
                    if(dashes == 1)
                    {
                        row2 = r; // sets first coordinates of "-"  
                        col2 = c; 
                        dashes++; 
                    }
                }
               if(available("O",r,c))
               {
                   letter++; 
               }
            }
            
            if(dashes == 2 && letter == 1 && board[row1+1][col1+1].equals("-") && board[row2+1][col2+1].equals("-")) 
            {
                coordinate [0] = row1; 
                coordinate [1] = col1; 
                return coordinate; 
            }
            
            row1 = 0; 
            col1 = 0;
        
            row2 = 0; 
            col2 = 0; 
        
            dashes = 0; 
            letter = 0; 
        }
        // goes through diagnols 
        for(int r = 0; r <= 2; r++)
        {
            for(int c = 0; c <= 2; c++)
            {
                if( r == 0  && c == 0  ||  r == 1  && c == 1 ||  r == 2  && c == 2)
                {   
                    if(available("-",r,c))
                    {
                        if(dashes == 0) // sets second coordinates of "-" 
                        {
                            row1 = r; 
                            col1 = c; 
                            dashes++;
                        }
                        if(dashes == 1)
                        {
                            row2 = r; // sets first coordinates of "-"  
                            col2 = c; 
                            dashes++; 
                        }
                    }
                    if(available("O",r,c))
                    {
                        letter++; 
                    }
                }

            }
        }
        
        if(letter == 1 && dashes == 2 && board[row1+1][col1+1].equals("-") && board[row2+1][col2+1].equals("-"))
        {
            coordinate[0] = row1;
            coordinate[1] = col1; 
            return coordinate; 
        }
        
        row1 = 0; 
        col1 = 0;
        
        row2 = 0; 
        col2 = 0; 
        
        dashes = 0; 
        letter = 0; 
        
        
        for(int r = 0; r <= 2; r++)
        {
            for(int c = 0; c <= 2; c++)
            {
                if( r == 2  && c == 0  ||  r == 1  && c == 1 ||  r == 0  && c == 2)
                {   
                    if(available("-",r,c))
                    {
                        if(dashes == 0) // sets second coordinates of "-" 
                        {
                            row1 = r; 
                            col1 = c; 
                            dashes++;
                        }
                        if(dashes == 1)
                        {
                            row2 = r; // sets first coordinates of "-"  
                            col2 = c; 
                            dashes++; 
                        }
                    }
                    if(available("O",r,c))
                    {
                        letter++; 
                    }
                }

            }
        }
        
        if(letter == 1 && dashes == 2 && board[row1+1][col1+1].equals("-") && board[row2+1][col2+1].equals("-"))
        {
            coordinate[0] = row1;
            coordinate[1] = col1; 
            return coordinate; 
        }
        
        
    coordinate[0] = 5;
    coordinate[1] = 5; 
    return coordinate; 
    
    
        
    }
          

    public static int[] lastSpot()
    {
        int [] lastSpot = {0,0}; 
        
        for(int r = 0; r < 3; r++)
        {
            for(int c = 0; c < 3; c++)
            {
                if(available("-",r,c))
                {
                    lastSpot[0] = r; 
                    lastSpot[1] = c; 
                    return lastSpot; 
                }
            }
        }
        int [] incorrect = {0,0}; 
        incorrect[0] = 7; 
        incorrect[1] = 7; 
        return incorrect; 
    }

    

    public static int[] setOne()
    {
        coordinate[0] = 0;
        coordinate[1] = 0;
        return coordinate; 
    }
    public static int[] setTwo()
    {
        coordinate[0] = 0;
        coordinate[1] = 1;
        return coordinate;
    }
    public static  int[] setThree()
    {
        coordinate[0] = 0;
        coordinate[1] = 2;
        return coordinate; 
    }
    public static int[] setFour()
    {
        coordinate[0] = 1;
        coordinate[1] = 0;
        return coordinate; 
    }
    public  static int[] setFive()
    {
      
        coordinate[0] = 1;
        coordinate[1] = 1;
        return coordinate; 
   
    }
    public static  int[] setSix()
    {
        coordinate[0] = 1;
        coordinate[1] = 2;
        return coordinate; 

    
    }
    public static int[] setSeven()
    {
    
        coordinate[0] = 2;
        coordinate[1] = 0;
        return coordinate; 
    }
    public static  int[] setEight()
    {
        coordinate[0] = 2;
        coordinate[1] = 1;
        return coordinate;
    }
    public static int[] setNine()
    {
        coordinate[0] = 2;
        coordinate[1] = 2;
        return coordinate; 
    }

    public static String getBoard(int row, int col)
    {
        return board[row+1][col+1]; 
    }

}