import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Christian Aguirre
 */
public class MorseCodeConverter {

    private static final MorseCodeTree tree = new MorseCodeTree();

    /*
    public static void main(String[] args){
        System.out.println(tree.toArrayList());
        //[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]
        System.out.println(printTree());
        //h s v i f u e l r a p w j  b d x n c k y t z g q m o
        System.out.println(convertToEnglish("--. --- --- -.. -... -.-- . / .-- --- .-. .-.. -.. "));
        //goodbye world
    }
     */

    public MorseCodeConverter(){

    }

    /**
     * Returns a string with all the data in the tree in LNR order with a space in between them.
     * Uses the toArrayList method in MorseCodeTree It should return the data in this order:
     * "h s v i f u e l r a p w j b d x n c k y t z g q m o"
     * Note the extra space between j and b - that is because there is an empty string that
     * is the root, and in the LNR traversal, the root would come between the right most
     * child of the left tree (j) and the left most child of the right tree (b).
     * This is used for testing purposes to make sure the MorseCodeTree has been built properly
     * @return the data in the tree in LNR order separated by a space.
     */

    public static String printTree()
    {
        ArrayList<String> treeData = tree.toArrayList();
        StringBuilder print = new StringBuilder();
        //For every element on the array list in the tree print
        //h s v i f u e l r a p w j  b d x n c k y t z g q m o
        //each letter with a space, the double space between the j and b is
        //the root
        for (String str : treeData)
            print.append(str).append(" ");


        return print.toString();
    }

    /**
     * Converts Morse code into English. Each letter is delimited by a space (‘ ‘).
     * Each word is delimited by a ‘/’.
     * Example:
     * code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
     * string returned = "Hello World"
     * @param code the morse code
     * @return the English translation
     */

    public static String convertToEnglish(String code)
    {
        StringBuilder englishWord = new StringBuilder();

        //codeWord is delimited by a ‘/’
        String[] codeWord = code.split(" / ");
        for (String str : codeWord) {
            //Each codeLetter is delimited by a space (‘ ‘).
            String[] codeLetter = str.split(" "); //Separates each letter before being decoded
            for (String str1 : codeLetter) {
                englishWord.append(tree.fetch(str1)); //Decipher
            }
            englishWord.append(" ");//Separates each word after it has been decoded
        }

        return englishWord.toString().trim(); //Trim spaces before and after sentences
    }

    /**
     * Converts a file of Morse code into English
     * Each letter is delimited by a space (‘ ‘).
     * Each word is delimited by a ‘/’.
     * Example:
     * a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
     * string returned = "Hello World"
     * @param codeFile name of the File that contains Morse Code
     * @return the English translation of the file
     * @throws FileNotFoundException if file is not found
     */
    public static String convertToEnglish(File codeFile) throws FileNotFoundException {
        StringBuilder englishWord = new StringBuilder();
        ArrayList<String> fileSentence = new ArrayList<>();
        String[] codeWord, codeLetter;

        //Create scanner class to open file
        Scanner inputFile = new Scanner(codeFile);

        //While loop to add items to arrayList
        while(inputFile.hasNext())
            fileSentence.add(inputFile.nextLine());
        inputFile.close(); //Close scanner after all items have been read

        //For every word in morse code sentence on arrayList use delimiter '/' to separate each word
        for (String str : fileSentence) {
            codeWord = str.split(" / ");
            //For every letter in morse code value use delimiter ' ' to separate each letter
            for (String str1 : codeWord) {
                codeLetter = str1.split(" ");
                //For every morse code value get the english letter equivalent
                for (String str2 : codeLetter) {
                    englishWord.append(tree.fetch(str2));
                }
                //add spaces between each word after it has been decoded
                englishWord.append(" ");
            }
        }
        //Trim outside spaces of string and return decoded sentence.
        return englishWord.toString().trim();
    }

}
