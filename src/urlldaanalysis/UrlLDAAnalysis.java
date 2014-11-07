/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package urlldaanalysis;

/**
 *
 * @author Administrator
 */
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class UrlLDAAnalysis {

    /**Class that analyzes a URL using LDA with Gibbs sampling (JGibbLDA implementation in Java, jgibblda.sourceforge.net) in order to extract salient topics and corresponding words
     * You can define the following inputs:
     * nTopics : number of Topics
     * alpha : alpha variable of LDA (suggested value from research work is 50 / number of Topics
     * beta: beta variable of LDA
     * niters : number of iterations
     * top_words : the amount of top words per topic that we would like to recognize
     * directory : the directory that we would like the results to be written in files
     * probability threshold : the threshold for the words that are going to be returned from the analysis (the lower the more lenient the analysis) 
     * 
     * Web parsing is performed using JSOUP (JSOUP.org)
     * The content that is provided to LDA as input consists of the textual content of the HTML of the url, along with the title and alt text of the images
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //example values
        String url_string="http://thesmartweb.eu";
        int nTopics=1;
        double alpha=50/nTopics;
        double beta=0.1;
        int niters=1000;
        int top_words=5;
        String directory="C:\\themis\\inputsurlldaanalysis\\";
        double probability_threshold=0.01;
        if(args!=null&&args.length==8){
            if(args[0]!=null){
                url_string=args[0];
            }
            if(args[1]!=null){
                nTopics=Integer.parseInt(args[1]);
            }
            if(args[2]!=null){
                alpha=Double.parseDouble(args[2]);
            }
            if(args[3]!=null){
                beta=Double.parseDouble(args[3]);
            }
            if(args[4]!=null){
                niters=Integer.parseInt(args[4]);
            }
            if(args[5]!=null){
                top_words=Integer.parseInt(args[5]);
            }
            if(args[6]!=null){
                directory=args[6];
            }
            if(args[7]!=null){
                probability_threshold=Double.parseDouble(args[7]);
            }
        }
        call(url_string,nTopics,alpha,beta,niters,top_words,directory);
        System.out.println("i ll try to read the top words file");
        ReadKeys rk = new ReadKeys();
        List<String> wordList = rk.readFile(directory, probability_threshold,top_words,nTopics);//the wordlist is created in case someone would like to use the words recognized
        System.out.println("i returned the wordlist");
        
    }
    
    public static void call(String url_string,int nTopics,double alpha,double beta,int niters,int top_words,String directory){
        try {
            //the content must have at first the number of documents to be analyzed with LDA and in the next line the content
            String content="1\n"+Parse(url_string);
            //the content is written in a file  "content_for_analysis.txt" in the directory that we have chosen
            File file_content_lda = new File(directory + "content_for_analysis.txt");
            FileUtils.writeStringToFile(file_content_lda, content);
            String directory_LDA=directory;
            LDAcall ldacall=new LDAcall();
            ldacall.LDAestimate(nTopics, directory_LDA , alpha,beta,niters, top_words);
        } catch (IOException ex) {
            Logger.getLogger(UrlLDAAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String Parse(String html_string){
        String content;
        //we extract the content from the html and clean it from stopwords and useless characters 
        content=cleanhtml(html_string);
        TextManipulation txtmanipulation=new TextManipulation();
        if(content!=null){
             content=txtmanipulation.removeChars(content);
             content=txtmanipulation.stop(content);
             content=txtmanipulation.removeChars(content);
        }
        return content;
      
    }
    
    public static String cleanhtml(String link_html){ 
        try {
            //we use JSOUP to connect and get the title, main text content and the alt text from the images
            Document doc = Jsoup.connect(link_html).timeout(10*1000).get();
            String title = doc.title();
            String mainbody = doc.body().text();
            Elements media = doc.select("[src]");
            //fix link html to remove https:// or http:// and simple /
            if(link_html.substring(link_html.length()-1,link_html.length()).equalsIgnoreCase("/")){link_html=link_html.substring(0,link_html.length()-1);}
            if(link_html.substring(0,5).equalsIgnoreCase("https")){ 
                link_html=link_html.substring(8);
            }else if(link_html.substring(0,4).equalsIgnoreCase("http")){ 
                link_html=link_html.substring(7);
            }
            
            String alttext="";
            
            //-------get alt text to internal images links
            for (Element medi : media) {
                if(medi.getElementsByTag("img").attr("src").toString().contains(link_html)){
                    alttext=alttext+" "+medi.getElementsByTag("img").attr("alt").toString();
                }
                if(medi.getElementsByTag("img").attr("src").toString().startsWith("/")){
                    alttext=alttext+" "+medi.getElementsByTag("img").attr("alt").toString();
                }
            }
            String content = mainbody + title + alttext;
            return content;
        } catch (IOException ex) {
            Logger.getLogger(UrlLDAAnalysis.class.getName()).log(Level.SEVERE, null, ex);
            String check=null;
            return check;
        }
        catch (NullPointerException ex) {
            Logger.getLogger(UrlLDAAnalysis.class.getName()).log(Level.SEVERE, null, ex);
            String check=null;
            return check;
        }
         catch (Exception ex) {
            Logger.getLogger(UrlLDAAnalysis.class.getName()).log(Level.SEVERE, null, ex);
            String check=null;
            return check;
        }
    
    }
    
    
}
