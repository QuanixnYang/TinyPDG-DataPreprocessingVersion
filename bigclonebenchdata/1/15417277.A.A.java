public class A{
    static Object loadPersistentRepresentationFromFile(URL url) throws PersistenceException {
        PersistenceManager.persistenceURL.get().addFirst(url);
        ObjectInputStream ois = null;
        HierarchicalStreamReader reader = null;
        XStream xstream = null;
        try {
            Reader inputReader = new java.io.InputStreamReader(url.openStream());
            try {
                XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                XMLStreamReader xsr = inputFactory.createXMLStreamReader(url.toExternalForm(), inputReader);
                reader = new StaxReader(new QNameMap(), xsr);
            } catch (XMLStreamException xse) {
                throw new PersistenceException("Error creating reader", xse);
            }
            xstream = new XStream(new StaxDriver());
            xstream.setClassLoader(Gate.getClassLoader());
            ois = xstream.createObjectInputStream(reader);
            Object res = null;
            Iterator urlIter = ((Collection) PersistenceManager.getTransientRepresentation(ois.readObject())).iterator();
            while (urlIter.hasNext()) {
                URL anUrl = (URL) urlIter.next();
                try {
                    Gate.getCreoleRegister().registerDirectories(anUrl);
                } catch (GateException ge) {
                    Err.prln("Could not reload creole directory " + anUrl.toExternalForm());
                }
            }
            res = ois.readObject();
            ois.close();
            return res;
        } catch (PersistenceException pe) {
            throw pe;
        } catch (Exception e) {
            throw new PersistenceException("Error loading GAPP file", e);
        } finally {
            PersistenceManager.persistenceURL.get().removeFirst();
            if (PersistenceManager.persistenceURL.get().isEmpty()) {
                PersistenceManager.persistenceURL.remove();
            }
        }
    }
}