package edu.iastate.cs228.hw4;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Benito Moeckly
 */

public class Decoder {

    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the file name here: ");                                                               //ask for the file name
        String filename = s.next();
        File f = new File(filename);
        int lines = 0;

        Scanner lScan = new Scanner(f);                                                                                 //we'll need two scanners
        Scanner codeScan = new Scanner(f);
        while(lScan.hasNextLine())                                                                                      //scan for the number off lines... i wish there was a better way to do this
        {
            lScan.nextLine();
            lines++;
        }
        String code;
        String binary;
        if(lines == 2)                                                                                                  //this is if the file only has two lines;
        {
            code = codeScan.nextLine();                                                                                 //line 1 is the code
            binary = codeScan.nextLine();                                                                               //line 2 is the binary for the message

        }
        else                                                                                                            //assume the file has 3 lines
        {
            code = codeScan.nextLine();
            code = code + '\n' + codeScan.nextLine();                                                                   //add the first two lines together and sandwich the \n in between since it gets lost in the scanner
            binary = codeScan.nextLine();                                                                               //the actual binary code for the message is line 3
        }

        MsgTree tree = new MsgTree(code);
        System.out.println("MESSAGE: ");
        tree.printCodes(tree, binary);

        int totalCharsOut = tree.outChars;                                                                              //here is some math for the statistics.
        int compressedBits = binary.length();                                                                           //we are making the assumption that the binary code is equal to the
        double uncompressedBits = totalCharsOut * 16.0;                                                                 //number of compressed bits (that's what everyone seems to think pls give me points)
        double bitsPerChar = compressedBits/(totalCharsOut*1.0);
        double spaceSaved = (1 - (compressedBits/uncompressedBits)) * 100;

        System.out.println("\nSTATISTICS: ");
        System.out.println("Avg bits/char: " + String.format("%5.2f", bitsPerChar));
        System.out.println("Total characters: "+totalCharsOut);
        System.out.println("Space Savings: " +String.format("%5.2f", spaceSaved)+'%');


    }
}
