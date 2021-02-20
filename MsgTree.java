package edu.iastate.cs228.hw4;

/**
 * @author benito moeckly
 */

public class MsgTree
{

    public char payloadChar;                                                                                            //character for leaf nodes
    public MsgTree left;                                                                                                //left child for this node
    public MsgTree right;                                                                                               //right child for this node
    public static int outChars = 0;                                                                                     //this is needed to print out the statistics

    private static int staticCharIdx = 0;                                                                                //Need static char idx to the tree string for recursive solution

    /**
     * Constructor for node with left and right children
      * @param encodingString the string that we will use to create the decoder MsgTree
     */
    public MsgTree(String encodingString)
    {

        char c = encodingString.charAt(staticCharIdx++);                                                                //we need to look at the current string in the encodingString depending on the static charIdx
        if(c == '^')                                                                                                    //look for a '^'... this tells us to make a new tree branch
        {
            left = new MsgTree(encodingString);                                                                         //make the left child the encoding string
            right = new MsgTree(encodingString);                                                                        //make right child with encoding string
        }
        else
        {
           this.payloadChar = c;                                                                                        //otherwise the current character is the payload char and this is a leaf node
           left = null; right = null;
        }

    }

    /**
     * Constructor for leaf node
      * @param payloadChar the character that this leaf node holds
     */
    public MsgTree(char payloadChar)
    {
        left = null;
        right = null;
        this.payloadChar = payloadChar;
    }

    /**
     *this prints a message from the given MsgTree and a binary string
     * @param root idk why we need to pass in the msg tree root if we can only call printCodes on the root msgTree but thatsCoolChamp
     * @param code the binary string we need to decode
     */
    public static void printCodes(MsgTree root, String code)
    {
        MsgTree r = root;                                                                                               //making another variable for the root in the method because just using root gave me problems (thanks java)
        MsgTree cursor = root;                                                                                          //this will act as our current msgTree node
        for(int i = 0; i < code.length(); i++)
        {

            if(code.charAt(i) == '0')                                                                                   //if we run into character '0'
            {
                cursor = cursor.left;                                                                                   //check the left child
            }
            else                                                                                                        //otherwise we run into '1'
            {
                cursor = cursor.right;                                                                                  //check the right child
            }
            if(cursor.left == null)                                                                                     //if the left child is null, we have a leaf node! i wanted an instance variable bool called isleaf but that didn't work for some reason :(
            {
                outChars++;                                                                                             //update the number of characters printed
                System.out.print(cursor.payloadChar);                                                                   //print character
                cursor = r;                                                                                             //bring the cursor back to the root
            }

        }
    }

}


