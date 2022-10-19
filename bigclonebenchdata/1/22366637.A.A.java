public class A{
    public int delete(BusinessObject o) throws DAOException {
        int delete = 0;
        Project project = (Project) o;
        try {
            PreparedStatement pst = connection.prepareStatement(XMLGetQuery.getQuery("DELETE_PROJECT"));
            pst.setInt(1, project.getId());
            delete = pst.executeUpdate();
            if (delete <= 0) {
                connection.rollback();
                throw new DAOException("Number of rows <= 0");
            } else if (delete > 1) {
                connection.rollback();
                throw new DAOException("Number of rows > 1");
            }
            connection.commit();
        } catch (SQLException e) {
            Log.write(e.getMessage());
            throw new DAOException("A SQLException has occured");
        } catch (NullPointerException npe) {
            Log.write(npe.getMessage());
            throw new DAOException("Connection null");
        }
        return delete;
    }
}