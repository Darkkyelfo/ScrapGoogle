import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GoogleScraping {
    /*Classe que responsavel por realizar um "Scraping" na primeira pagina do google
    * Recebe a o texto a ser buscado e retorna um arquivo com titulo,link e descricao
    * dos primeiros resultados.
    */
    private String url;


    public GoogleScraping(String search) {
        this.url = String.format("https://www.google.com/search?q=%s",search);
    }


    public String startScraping(String filename) throws IOException {
        Document doc = Jsoup.connect(this.url).get();
        Elements links = doc.select("div.rc");
        StringBuilder result = new StringBuilder();
        for(Element link:links){
            String title = link.select("div.yuRUbf").select("h3.LC20lb").first().text();
            String urlLink = link.select("div.yuRUbf").select("a[href]").attr("href");
            String description = link.select("div.IsZvec").select("span.aCOpRe").text();
            result.append(String.format("%s\t%s\t%s", title, urlLink, description)).append("\n");
        }
        this.writeResult(filename,result.toString());
        return result.toString();
    }

    private void writeResult(String fileName,String result) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("TITULO\tLINK\tDESCRICAO");
        printWriter.print(result);
        printWriter.close();
    }
}
