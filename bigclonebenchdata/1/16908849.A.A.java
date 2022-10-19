public class A{
    public static void changeStructure(double version) throws DException {
        try {
            Class cl = Class.forName("com.daffodilwoods.daffodildb.server.datadictionarysystem.SystemTablesCreator");
            java.net.URL urlw = cl.getResource("/com/daffodilwoods/daffodildb/server/datadictionarysystem/systemtablesStructure.obj");
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(urlw.openStream()));
            structureMap = (HashMap) ois.readObject();
            if (version >= 3.4) {
                Object[][] columnsTableStructure = (Object[][]) structureMap.get((Object) SystemTables.columns_TableName);
                columnsTableStructure[9][2] = new Integer(1027);
            }
            ois.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }
}