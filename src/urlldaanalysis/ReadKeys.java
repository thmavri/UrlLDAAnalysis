/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urlldaanalysis;
/**
 *
 * @author Themis Mavridis
 */
import java.util.ArrayList;
import java.io.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import java.util.*;
import java.util.List;
/*Class that reads the file of top words and
 * creates a words.txt files that contains the words that satisfy the probability threshold
 */


public class ReadKeys {
      public List<String> readFile(String example_dir,Double prob_threshold,int top_words,int nTopics)  {
        try {
            TextManipulation textualmanipulation=new TextManipulation();
           
            Collection<File> inputfiles = textualmanipulation.getinputfiles(example_dir,"twords");
            String[] inarr=new String[inputfiles.size()];
            int j=0;
            for (File file : inputfiles){
                inarr[j]=file.getPath();
                j++;
            }
            int size = inarr.length * top_words * nTopics;
            String[] line = new String[size];
            File file_words = new File(example_dir + "words.txt");
            int op = 0;
            for (int i = 0; i < inarr.length; i++) {
                try {
                    FileInputStream fstream = null;
                    fstream = new FileInputStream(inarr[i]);
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String test_line;
                    //read the lines of the files and get the words that are not numbers
                    while ((test_line = br.readLine()) != null) {
                        if (test_line.startsWith("\t")) {
                            String li = test_line.trim();
                            String check = li.split(" ")[0].trim();
                            boolean flag_check_number = this.checkIfNumber(check);
                            if (flag_check_number == false) {
                                Double check_prob = Double.parseDouble(li.split(" ")[1].trim());
                                if (check_prob.compareTo(prob_threshold)>0) {
                                    line[op] = check;
                                    op = op + 1;
                                }
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ReadKeys.class.getName()).log(Level.SEVERE, null, ex);
                     ArrayList<String> finalList = new ArrayList<String>();
                    return finalList;
                }
            }
            List<String> stringList = new ArrayList<String>();
            for (String string : line) {
                if (string != null && string.length() > 0) {
                    stringList.add(string);
                }
            }
            stringList=textualmanipulation.clearListString(stringList);
            FileUtils.writeLines(file_words,stringList);
            return stringList;
        } catch (IOException ex) {
            Logger.getLogger(ReadKeys.class.getName()).log(Level.SEVERE, null, ex);
            ArrayList<String> emptyList = new ArrayList<String>();
            return emptyList;
        }
    }
     public boolean checkIfNumber(String in) {
        try {   
            Double.parseDouble(in);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
        
    }
}
