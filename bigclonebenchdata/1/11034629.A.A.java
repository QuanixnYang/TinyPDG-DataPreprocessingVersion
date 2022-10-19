public class A{
    private List _getWeathersFromYahoo(String city) {
        System.out.println("== get weather information of " + city + " from yahoo ==");
        try {
            URL url = new URL(URL + cities.get(city).toString());
            InputStream input = url.openStream();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(false);
            SAXParser parser = factory.newSAXParser();
            YahooHandler yh = new YahooHandler();
            yh.setCity(city);
            parser.parse(input, yh);
            return yh.getWeathers();
        } catch (MalformedURLException e) {
            throw new WeatherException("MalformedURLException");
        } catch (IOException e) {
            throw new WeatherException("无法读取数据。");
        } catch (ParserConfigurationException e) {
            throw new WeatherException("ParserConfigurationException");
        } catch (SAXException e) {
            throw new WeatherException("数据格式错误，无法解析。");
        }
    }
}