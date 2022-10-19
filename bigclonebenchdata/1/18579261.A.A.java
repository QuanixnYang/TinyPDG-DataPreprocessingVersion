public class A{
    public IContentExtension[] getContentExtensions(String locale) {
        if (RemoteHelp.isEnabled()) {
            List contributions = new ArrayList();
            PreferenceFileHandler handler = new PreferenceFileHandler();
            String isEnabled[] = handler.isEnabled();
            for (int ic = 0; ic < handler.getTotalRemoteInfocenters(); ic++) {
                if (isEnabled[ic].equalsIgnoreCase("true")) {
                    InputStream in = null;
                    try {
                        URL url = RemoteHelp.getURL(ic, PATH_EXTENSIONS);
                        in = url.openStream();
                        if (reader == null) {
                            reader = new DocumentReader();
                        }
                        UAElement element = reader.read(in);
                        IContentExtension[] children = (IContentExtension[]) element.getChildren(IContentExtension.class);
                        for (int contrib = 0; contrib < children.length; contrib++) {
                            contributions.add(children[contrib]);
                        }
                    } catch (IOException e) {
                        String msg = "I/O error while trying to contact the remote help server";
                        HelpBasePlugin.logError(msg, e);
                    } catch (Throwable t) {
                        String msg = "Internal error while reading topic extensions from remote server";
                        HelpBasePlugin.logError(msg, t);
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }
            return (IContentExtension[]) contributions.toArray(new IContentExtension[contributions.size()]);
        }
        return new IContentExtension[0];
    }
}