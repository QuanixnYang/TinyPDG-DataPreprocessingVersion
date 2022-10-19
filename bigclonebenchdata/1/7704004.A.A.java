public class A{
    public void moveRowDown(int id, int row) throws FidoDatabaseException {
        try {
            Connection conn = null;
            Statement stmt = null;
            try {
                conn = fido.util.FidoDataSource.getConnection();
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                int max = findMaxRank(stmt, id);
                if ((row < 1) || (row > (max - 1))) throw new IllegalArgumentException("Row number not between 1 and " + (max - 1));
                stmt.executeUpdate("update InstructionGroups set Rank = -1 where InstructionId = '" + id + "' and Rank = " + row);
                stmt.executeUpdate("update InstructionGroups set Rank = " + row + " where InstructionId = '" + id + "' and Rank = " + (row + 1));
                stmt.executeUpdate("update InstructionGroups set Rank = " + (row + 1) + " where InstructionId = '" + id + "' and Rank = -1");
                conn.commit();
            } catch (SQLException e) {
                if (conn != null) conn.rollback();
                throw e;
            } finally {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException e) {
            throw new FidoDatabaseException(e);
        }
    }
}