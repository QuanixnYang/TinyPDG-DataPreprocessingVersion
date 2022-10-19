public class A{
    public Savable loadResource(String name, PrimitiveLoader loader) {
        Savable objeto = null;
        URL url = ResourceLocator.locateFile(loader.getBaseFolder(), name, loader.getCompiledExtension());
        if (url == null) {
            url = ResourceLocator.locateFile(loader.getBaseFolder(), name, loader.getPrimitiveExtension());
            if (url != null) {
                try {
                    objeto = loader.loadResource(name, url.openStream());
                    File file = ResourceLocator.replaceExtension(url, loader.getCompiledExtension());
                    BinaryExporter.getInstance().save(objeto, file);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                objeto = BinaryImporter.getInstance().load(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objeto;
    }
}