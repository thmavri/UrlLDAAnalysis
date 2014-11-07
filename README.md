UrlLDAAnalysis
==============
It analyzes a URL using LDA with Gibbs sampling (JGibbLDA implementation in Java, jgibblda.sourceforge.net) in order to extract salient topics and corresponding words

     * You can define the following inputs:
     * nTopics : number of Topics
     * alpha : alpha variable of LDA (suggested value from research work is 50 / number of Topics
     * beta: beta variable of LDA
     * niters : number of iterations
     * top_words : the amount of top words per topic that we would like to recognize
     * directory : the directory that we would like the results to be written in files
     * probability threshold : the threshold for the words that are going to be returned from the analysis (the lower the more lenient the analysis) 


Web parsing is performed using JSOUP (JSOUP.org)

     * The content that is provided to LDA as input consists of the textual content of the HTML of the url, along with the title and alt text of the images
     * @param args the command line arguments
     */
