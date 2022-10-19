public class A{
    public long create(long mimeTypeId, long sanId) throws SQLException {
        long fileId = 0;
        DataSource ds = getDataSource(DEFAULT_DATASOURCE);
        Connection conn = ds.getConnection();
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.execute(NEXT_FILE_ID);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                fileId = rs.getLong(NEXTVAL);
            }
            PreparedStatement pstmt = conn.prepareStatement(INSERT_FILE);
            pstmt.setLong(1, fileId);
            pstmt.setLong(2, mimeTypeId);
            pstmt.setLong(3, sanId);
            pstmt.setLong(4, WORKFLOW_ATTENTE_VALIDATION);
            int nbrow = pstmt.executeUpdate();
            if (nbrow == 0) {
                throw new SQLException();
            }
            conn.commit();
            closeRessources(conn, pstmt);
        } catch (SQLException e) {
            log.error("Can't FileDAOImpl.create " + e.getMessage());
            conn.rollback();
            throw e;
        }
        return fileId;
    }
}