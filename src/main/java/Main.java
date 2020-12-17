public class Main {
    public static void main(String[] args) {
        GoogleScraping googleScraping = new GoogleScraping("JHipster");
        try{
            System.out.println(googleScraping.startScraping("googleScrap.txt"));
        } catch (Exception e){
            System.out.println("ERRO "+e.getMessage());
        }
    }
}
