/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package urlldaanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Administrator
 */
public class TextManipulation {
    public List<String> clearListString(List<String> wordList){
            //remove all null elements of the wordlist
            wordList.removeAll(Collections.singleton(null));
            //remove the duplicate elements since HashSet does not allow duplicates
            HashSet<String> hashSet_wordList = new HashSet<String>(wordList);
            //create an iterator to the hashset to add the elements back to the wordlist
            Iterator wordList_iterator=hashSet_wordList.iterator();
            //clear the wordlist
            wordList.clear();
            while(wordList_iterator.hasNext()){
                    wordList.add(wordList_iterator.next().toString());
            }
            return wordList;         
                    
    }
     public boolean AppendWordList(List<String> wordList, String file_wordlist){
            //----------------append the wordlist to a file
                        File wordlist_file = new File(file_wordlist);
                        try {
                            FileUtils.writeLines(wordlist_file, wordList);
                            return true;
                        } catch (IOException ex) {
                            Logger.getLogger(TextManipulation.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                        //-----------------------------------------        
                    
    }
     public boolean AppendString(String input, String file_string){
            //----------------append the wordlist to a file
                        File string_file = new File(file_string);
                        try {
                            FileUtils.writeStringToFile(string_file, input);
                            return true;
                        } catch (IOException ex) {
                            Logger.getLogger(TextManipulation.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                        //-----------------------------------------        
                    
    }
    public List<String> AddAList(List<String> wordListtoAdd, List<String> wordListTotal){
            Iterator wordList_new_final_iterator=wordListtoAdd.iterator();
            while(wordList_new_final_iterator.hasNext()){
                    wordListTotal.add(wordList_new_final_iterator.next().toString());
            }
            return wordListTotal;
                    
    }
    public boolean FileTypeAnalyzed(String input){
        List<String> filetypesnotsupported=new ArrayList<String>();
        filetypesnotsupported.add(".pdf");
        filetypesnotsupported.add(".ppt");
        filetypesnotsupported.add(".doc");
        Iterator filesiterator=filetypesnotsupported.iterator();
        boolean flag_found=false;
        while(filesiterator.hasNext()&&flag_found){
            if(filesiterator.next().toString().contains(input)){
                flag_found=true;
                return flag_found;
            }
        }
        return flag_found;
    }
    public String removeChars(String str){
        if (str != null) {
            try {
                //str = str.replaceAll("(\r\n|\r|\n|\n\r)", " "); //Clear Paragraph escape sequences
                str = str.replaceAll("\\.", " "); //Clear dots
                str = str.replaceAll("\\-", " "); //
                str = str.replaceAll("\\_", " "); //
                str = str.replaceAll(":", " ");
                str = str.replaceAll("\\+", " ");
                str = str.replaceAll("\\/", " ");
                str = str.replaceAll("\\|", " ");
                str = str.replaceAll("\\[", " ");
                str = str.replaceAll("\\?", " ");
                str = str.replaceAll("\\#", " ");
                str = str.replaceAll("\\!", " ");
                str = str.replaceAll("'", " "); //Clear apostrophes
                str = str.replaceAll(",", " "); //Clear commas
                str = str.replaceAll("@", " "); //Clear @'s (optional)
                str = str.replaceAll("$", " "); //Clear $'s (optional)
                str = str.replaceAll("\\\\", "**&**"); //Clear special character backslash 4 \'s due to regexp format
                str = str.replaceAll("&amp;", "&"); //change &amp to &
                str = str.replaceAll("&lt;", "<"); //change &lt; to <
                str = str.replaceAll("&gt;", ">"); //change &gt; to >
                //		str = str.replaceAll("<[^<>]*>"," ");		//drop anything in <>
                str = str.replaceAll("&#\\d+;", " "); //change &#[digits]; to space
                str = str.replaceAll("&quot;", " "); //change &quot; to space
                //		str = str.replaceAll("http://[^ ]+ "," ");	//drop urls
                str = str.replaceAll("-", " "); //drop non-alphanumeric characters
                str = str.replaceAll("[^0-9a-zA-Z ]", " "); //drop non-alphanumeric characters
                str = str.replaceAll("&middot;", " ");
                str = str.replaceAll("\\>", " ");
                str = str.replaceAll("\\<", " ");
                str = str.replaceAll("<[^>]*>", "");
                str = str.replaceAll("\\d"," ");
                //str=str.replaceAll("\\<.*?\\>", "");
                str = str.replace('β', ' ');
                str = str.replace('€', ' ');
                str = str.replace('™', ' ');
                str = str.replace(')', ' ');
                str = str.replace('(', ' ');
                str = str.replace('[', ' ');
                str = str.replace(']', ' ');
                str = str.replace('`', ' ');
                str = str.replace('~', ' ');
                str = str.replace('!', ' ');
                str = str.replace('#', ' ');
                str = str.replace('%', ' ');
                str = str.replace('^', ' ');
                str = str.replace('*', ' ');
                str = str.replace('&', ' ');
                str = str.replace('_', ' ');
                str = str.replace('=', ' ');
                str = str.replace('+', ' ');
                str = str.replace('|', ' ');
                str = str.replace('\\', ' ');
                str = str.replace('{', ' ');
                str = str.replace('}', ' ');
                str = str.replace(',', ' ');
                str = str.replace('.', ' ');
                str = str.replace('/', ' ');
                str = str.replace('?', ' ');
                str = str.replace('"', ' ');
                str = str.replace(':', ' ');
                str = str.replace('>', ' ');
                str = str.replace(';', ' ');
                str = str.replace('<', ' ');
                str = str.replace('$', ' ');
                str = str.replace('-', ' ');
                str = str.replace('@', ' ');
                str = str.replace('©', ' ');
                //remove space
                InputStreamReader in = new InputStreamReader(IOUtils.toInputStream(str));
                BufferedReader br = new BufferedReader(in);
                Pattern p;
                Matcher m;
                String afterReplace = "";
                String strLine;
                String inputText = "";
                while ((strLine = br.readLine()) != null) {
                    inputText = strLine;
                    p = Pattern.compile("\\s+");
                    m = p.matcher(inputText);
                    afterReplace = afterReplace + m.replaceAll(" ");
                }
                br.close();
                str = afterReplace;
                return str;
            } catch (IOException ex) {
                Logger.getLogger(TextManipulation.class.getName()).log(Level.SEVERE, null, ex);
                str=null;
                return str;
            }
        } else {
            return str;
        }
    }
   
    public List<String> sortHashmap (final HashMap<String,Double> map){
        Set<String> set = map.keySet();
        List<String> keys=new ArrayList<String>(set);
        Collections.sort(keys,new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
               return Double.compare(map.get(s2), map.get(s1));
            }
        });
        return keys;
    }
    public Collection<File> getinputfiles(String directory_path,String filetype){
            String[] extensions = {filetype};//set the file extensions you would like to parse, e.g. you could have {txt,jpeg,pdf}
            File directory = new File(directory_path);
            //----FileUtils listfiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter)
      
            //---- file filter is set to the extensions
            //---- the dirFilter is set to true and it performs recursive search to all the subdirectories
            String collection = FileUtils.listFiles(directory, extensions, true).toString();
            Collection<File> Files = FileUtils.listFiles(directory, extensions, true);
            String[] paths = new String[Files.size()];//----the String array will contain all the paths of the files
            int j=0;
            for (File file : Files) {
                paths[j]=file.getPath();
                j++;
            }
            return Files;
}
    public String stop(String input){
        String[] stoparr={

        "a",
        "able",
        "and",
        "about",
        "above",
        "according",
        "accordingly",
        "across",
        "actually",
        "after",
        "afterwards",
        "again",
        "against",
        "all",
        "allow",
        "allows",
        "almost",
        "alone",
        "along",
        "already",
        "also",
        "although",
        "always",
        "am",
        "among",
        "amongst",
        "an",
        "and",
        "another",
        "any",
        "anybody",
        "anyhow",
        "anyone",
        "anything",
        "anyway",
        "anyways",
        "anywhere",
        "apart",
        "appear",
        "appreciate",
        "appropriate",
        "are",
        "around",
        "as",
        "aside",
        "ask",
        "asking",
        "associated",
        "at",
        "available",
        "away",
        "awfully",
        "b",
        "B",
        "be",
        "became",
        "because",
        "become",
        "becomes",
        "becoming",
        "been",
        "before",
        "beforehand",
        "behind",
        "being",
        "believe",
        "below",
        "beside",
        "besides",
        "best",
        "better",
        "between",
        "beyond",
        "both",
        "brief",
        "but",
        "by",
        "c",
        "came",
        "can",
        "cannot",
        "cant",
        "cause",
        "causes",
        "certain",
        "certainly",
        "changes",
        "clearly",
        "co",
        "com",
        "come",
        "comes",
        "concerning",
        "consequently",
        "consider",
        "considering",
        "contain",
        "containing",
        "contains",
        "corresponding",
        "could",
        "course",
        "currently",
        "d",
        "definitely",
        "described",
        "despite",
        "did",
        "different",
        "do",
        "does",
        "doing",
        "done",
        "down",
        "downwards",
        "during",
        "e",
        "each",
        "edu",
        "eg",
        "eight",
        "either",
        "else",
        "elsewhere",
        "enough",
        "entirely",
        "especially",
        "et",
        "etc",
        "even",
        "ever",
        "every",
        "everybody",
        "everyone",
        "everything",
        "everywhere",
        "ex",
        "exactly",
        "example",
        "except",
        "f",
        "far",
        "few",
        "fifth",
        "first",
        "five",
        "followed",
        "following",
        "follows",
        "for",
        "former",
        "formerly",
        "forth",
        "four",
        "from",
        "further",
        "furthermore",
        "g",
        "get",
        "gets",
        "getting",
        "given",
        "gives",
        "go",
        "goes",
        "going",
        "gone",
        "got",
        "gotten",
        "greetings",
        "h",
        "had",
        "happens",
        "hardly",
        "has",
        "have",
        "having",
        "he",
        "hello",
        "help",
        "hence",
        "her",
        "here",
        "hereafter",
        "hereby",
        "herein",
        "hereupon",
        "hers",
        "herself",
        "hi",
        "him",
        "himself",
        "his",
        "hither",
        "hopefully",
        //"how",
        "howbeit",
        "however",
        "i",
        "ie",
        "if",
        "ignored",
        "immediate",
        "in",
        "inasmuch",
        "inc",
        "indeed",
        "indicate",
        "indicated",
        "indicates",
        "inner",
        "insofar",
        "instead",
        "into",
        "inward",
        "is",
        "it",
        "its",
        "itself",
        "j",
        "just",
        "k",
        "keep",
        "keeps",
        "kept",
        "know",
        "knows",
        "known",
        "l",
        "last",
        "lately",
        "later",
        "latter",
        "latterly",
        "least",
        "less",
        "lest",
        "let",
        "like",
        "liked",
        "likely",
        "little",
        "ll", //added to avoid words like you'll,I'll etc.
        "look",
        "looking",
        "looks",
        "ltd",
        "m",
        "mainly",
        "many",
        "may",
        "maybe",
        "me",
        "mean",
        "meanwhile",
        "merely",
        "might",
        "more",
        "moreover",
        "most",
        "mostly",
        "much",
        "must",
        "my",
        "myself",
        "n",
        "name",
        "namely",
        "nd",
        "near",
        "nearly",
        "necessary",
        "need",
        "needs",
        "neither",
        "never",
        "nevertheless",
        "new",
        "next",
        "nine",
        "no",
        "nobody",
        "non",
        "none",
        "noone",
        "nor",
        "normally",
        "not",
        "nothing",
        "novel",
        "now",
        "nowhere",
        "o",
        "obviously",
        "of",
        "off",
        "often",
        "oh",
        "ok",
        "okay",
        "old",
        "on",
        "once",
        "one",
        "ones",
        "only",
        "onto",
        "or",
        "other",
        "others",
        "otherwise",
        "ought",
        "our",
        "ours",
        "ourselves",
        "out",
        "outside",
        "over",
        "overall",
        "own",
        "p",
        "particular",
        "particularly",
        "per",
        "perhaps",
        "placed",
        "please",
        "plus",
        "possible",
        "presumably",
        "probably",
        "provides",
        "q",
        "que",
        "quite",
        "qv",
        "r",
        "rather",
        "rd",
        "re",
        "really",
        "reasonably",
        "regarding",
        "regardless",
        "regards",
        "relatively",
        "respectively",
        "right",
        "s",
        "said",
        "same",
        "saw",
        "say",
        "saying",
        "says",
        "second",
        "secondly",
        "see",
        "seeing",
        "seem",
        "seemed",
        "seeming",
        "seems",
        "seen",
        "self",
        "selves",
        "sensible",
        "sent",
        "serious",
        "seriously",
        "seven",
        "several",
        "shall",
        "she",
        "should",
        "since",
        "six",
        "so",
        "some",
        "somebody",
        "somehow",
        "someone",
        "something",
        "sometime",
        "sometimes",
        "somewhat",
        "somewhere",
        "soon",
        "sorry",
        "specified",
        "specify",
        "specifying",
        "still",
        "sub",
        "such",
        "sup",
        "sure",
        "t",
        "take",
        "taken",
        "tell",
        "tends",
        "th",
        "than",
        "thank",
        "thanks",
        "thanx",
        "that",
        "thats",
        "the",
        "their",
        "theirs",
        "them",
        "themselves",
        "then",
        "thence",
        "there",
        "thereafter",
        "thereby",
        "therefore",
        "therein",
        "theres",
        "thereupon",
        "these",
        "they",
        "think",
        "third",
        "this",
        "thorough",
        "thoroughly",
        "those",
        "though",
        "three",
        "through",
        "throughout",
        "thru",
        "thus",
        "to",
        "together",
        "too",
        "took",
        "toward",
        "towards",
        "tried",
        "tries",
        "truly",
        "try",
        "trying",
        "twice",
        "two",
        "u",
        "un",
        "under",
        "unfortunately",
        "unless",
        "unlikely",
        "until",
        "unto",
        "up",
        "upon",
        "us",
        "use",
        "used",
        "useful",
        "uses",
        "using",
        "usually",
        "uucp",
        "v",
        "value",
        "various",
        "ve", //added to avoid words like I've,you've etc.
        "very",
        "via",
        "viz",
        "vs",
        "w",
        "want",
        "wants",
        "was",
        "way",
        "we",
        "welcome",
        "well",
        "went",
        "were",
        "what",
        "whatever",
        "when",
        "whence",
        "whenever",
        "where",
        "whereafter",
        "whereas",
        "whereby",
        "wherein",
        "whereupon",
        "wherever",
        "whether",
        "which",
        "with",
        "while",
        "whither",
        "who",
        "whoever",
        "whole",
        "whom",
        "whose",
        "why",
        "will",
        "willing",
        "wish",
        "with",
        "within",
        "without",
        "wonder",
        "would",
        "would",
        "x",
        "y",
        "yes",
        "yet",
        "you",
        "your",
        "yours",
        "yourself",
        "yourselves",
        "z",
        "zero",
        "-",
        "_",
        "+",
        ".",
        "&",
        "|"};
        input=input.toLowerCase();
        String[] inarr=input.split(" ");
        for(int i=0;i<stoparr.length;i++){
            for (int j = 0; j < inarr.length; j++){
              if (inarr[j].equals(stoparr[i])) {
                    inarr[j]=" ";
              }
            }
        }
      String output="";
      for (int k=0;k<inarr.length;k++) {
        output=output+inarr[k]+" ";
      }
      return output;
  }
    
}
