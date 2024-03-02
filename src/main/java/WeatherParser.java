import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

public class WeatherParser {
    public static void main(String[] args) {
        Document doc = getWeatherComDocument();
        /*System.out.println(doc);*/
        Elements dayEntries = doc.select(".weather-forecast-longterm-list-entry");
        //System.out.println(days);

        dayEntries.forEach(element -> {
            String date = element.selectFirst(".weather-forecast-longterm-list-entry-hour .date").text();
            String day = element.selectFirst(".weather-forecast-longterm-list-entry-hour .day").text();
            Element tempEntry = element.selectFirst(".weather-forecast-longterm-list-entry-forecast");
            String temp = tempEntry.selectFirst(".weather-forecast-longterm-list-entry-forecast-temp").text();
            String lowTemp = tempEntry.selectFirst(".weather-forecast-longterm-list-entry-forecast-lowtemp").text();

            System.out.println(date + " " + day + ", " + temp + " " + lowTemp);
        });
    }

    private static Document getWeatherComDocument() {
        Document doc = null;
        try {
             doc = Jsoup.connect("https://pogoda.interia.pl/prognoza-dlugoterminowa-krakow,cId,4970")
                     .get();
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return doc;
    }

}
