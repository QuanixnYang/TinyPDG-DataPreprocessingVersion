public class A{
    public static void storePolicy(DBBroker broker, XmldbURI docPath) throws EXistException, IOException {
        XmldbURI docName = docPath.lastSegment();
        URL url = XACMLUtil.class.getResource(docPath.toString());
        if (url == null) return;
        String content = toString(url.openStream());
        if (content == null) return;
        Collection collection = getPolicyCollection(broker);
        if (collection == null) return;
        TransactionManager transact = broker.getBrokerPool().getTransactionManager();
        Txn txn = transact.beginTransaction();
        try {
            IndexInfo info = collection.validateXMLResource(txn, broker, docName, content);
            collection.store(txn, broker, info, content, false);
            transact.commit(txn);
        } catch (Exception e) {
            transact.abort(txn);
            if (e instanceof EXistException) throw (EXistException) e;
            throw new EXistException("Error storing policy '" + docPath + "'", e);
        }
    }
}