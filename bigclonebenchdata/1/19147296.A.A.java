public class A{
    public boolean ponerFlotantexRonda(int idJugadorDiv, int idRonda, int dato) {
        int intResult = 0;
        String sql = "UPDATE jugadorxdivxronda " + " SET flotante = " + dato + " WHERE jugadorxDivision_idJugadorxDivision = " + idJugadorDiv + " AND ronda_numeroRonda = " + idRonda;
        try {
            connection = conexionBD.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            intResult = ps.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException exe) {
                exe.printStackTrace();
            }
        } finally {
            conexionBD.close(ps);
            conexionBD.close(connection);
        }
        return (intResult > 0);
    }
}