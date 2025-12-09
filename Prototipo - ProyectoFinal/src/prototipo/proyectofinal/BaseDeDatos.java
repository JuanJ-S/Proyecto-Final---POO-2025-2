package prototipo.proyectofinal;
import java.sql.*;

public class BaseDeDatos {
    private static String url = "jdbc:mysql://localhost:3306/conjunto";
    private static String user = "root";
    private static String password = "Jj10302526";
    
    //Inicio de Sesion
    public static boolean inicioSesion(int idSistema, String contraseña, int rol){
        String contraseñaBD = null; //Variable para comparar contraseña de BD
        int rolBD = 0; //Variable para comparar rol de BD
        boolean resultado = false; //Retorno
        String sql = "Select * from usuario where idSesion = ?"; //Comando a usar
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){ //Conexion y trabajo con BD
            ps.setInt(1, idSistema); //Reemplazar ? por dato dado
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                rolBD = rs.getInt(3);
                contraseñaBD = rs.getString(5);
            }
            resultado = (contraseñaBD.equals(contraseña)&rolBD==rol); //Compara contraseña dada con contraseña en BD
        } catch(Exception e){ //Reporte de Errores en el proceso
            if(resultado == false){
                System.out.println("Contraseña o Rol equivocados");
            } else{ 
                System.out.println("El ID ingresado no existe");
            }
        }
        return resultado;
    }
    
    public static Residente construirResidente(int idSistema){
        Residente residente = null; //Retorno
        //Atributos del retorno
        String nombres = null;
        String apellidos = null;
        int idResidente = 0;
        int idApto = 0;
        String sql = "Select * from residente where idSesion = ?"; //Comando SQL
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idSistema);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                nombres = rs.getString(1);
                apellidos = rs.getString(2);
                idResidente = rs.getInt(3);
                idApto = rs.getInt(4);
            }
            residente = new Residente(idResidente, idApto, nombres, apellidos, idSistema);
        } catch(Exception e){
            System.out.println("Error Inesperado: "+e);
        }
        
        return residente;
    }
    
    public static Administrador construirAdmin(int idSistema){
        Administrador admin = null; //Retorno
        //Atributos del retorno
        String nombres = null;
        String apellidos = null;
        String sql = "Select * from usuario where idSesion = ?"; //Comando SQL
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idSistema);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                nombres = rs.getString(1);
                apellidos = rs.getString(2);
            }
            admin = new Administrador(nombres, apellidos, idSistema);
        } catch(Exception e){
            System.out.println("Error Inesperado: "+e);
        }
        
        return admin;
    }
    
    public static Vigilante construirVigilante(int idSistema){
        Vigilante vigilante = null; //Retorno
        //Atributos del retorno
        String nombres = null;
        String apellidos = null;
        String sql = "Select * from usuario where idSesion = ?"; //Comando SQL
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idSistema);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                nombres = rs.getString(1);
                apellidos = rs.getString(2);
            }
            vigilante = new Vigilante(nombres, apellidos, idSistema);
        } catch(Exception e){
            System.out.println("Error Inesperado: "+e);
        }
        
        return vigilante;
    }

    // Métodos nuevos para compatibilidad con Residente y GUI
    public static String consultarVisitasPendientes(int idApto) {
        StringBuilder resultado = new StringBuilder();
        String sql = "select idVisitante, nombres, apellidos, fechaDeEntrada FROM visitante WHERE idDestino = ? AND isAprobado = false;";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt(2))
                        .append(", Nombre: ").append(rs.getString(3)).append(" ").append(rs.getString(4))
                        .append(", Entrada: ").append(rs.getTimestamp(7)).append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }

    public static void aprobarVisita(int idApto, int idVisitante) {
        String sql = "UPDATE visitante SET isAprobado = true WHERE idVisitante = ? AND idDestino = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVisitante);
            ps.setInt(2, idApto);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al aprobar visita: " + e.getMessage());
        }
    }

    public static String consultarVisitantes(int idApto) {
        StringBuilder resultado = new StringBuilder();
        String sql = "SELECT idVisitante, nombres, apellidos, fechaDeEntrada, fechaDesalida FROM visitante WHERE idDestino = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("idVisitante"))
                        .append(", Nombre: ").append(rs.getString("nombres")).append(" ").append(rs.getString("apellidos"))
                        .append(", Entrada: ").append(rs.getTimestamp("fechaDeEntrada"))
                        .append(", Salida: ").append(rs.getTimestamp("fechaDesalida") != null ? rs.getTimestamp("fechaDesalida") : "Pendiente").append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }

    public static String consultarPaquetes(int idApto) {
        StringBuilder resultado = new StringBuilder();
        String sql = "SELECT idPaquete, remitente, fechaDeLlegada, isEntregado FROM paquete WHERE idDestino = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("idPaquete"))
                        .append(", Remitente: ").append(rs.getString("remitente"))
                        .append(", Llegada: ").append(rs.getTimestamp("fechaDeLlegada"))
                        .append(", Entregado: ").append(rs.getBoolean("isEntregado") ? "Sí" : "No").append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }

    public static String consultarPaquetesPorDia(int idApto, String dia) {
        StringBuilder resultado = new StringBuilder();
        String sql = "SELECT idPaquete, remitente, fechaDeLlegada, isEntregado FROM paquete WHERE idDestino = ? AND DATE(fechaDeLlegada) = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ps.setString(2, dia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("idPaquete"))
                        .append(", Remitente: ").append(rs.getString("remitente"))
                        .append(", Llegada: ").append(rs.getTimestamp("fechaDeLlegada"))
                        .append(", Entregado: ").append(rs.getBoolean("isEntregado") ? "Sí" : "No").append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }
    
    //Funciones Vigilante
    public static void registrarVisitante(int idVisitante, String nombres, String apellidos, int idDestino){
        String sql = "insert into visitante values(null,?,?,?,?,false,now(),null)";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idVisitante);
            ps.setString(2, nombres);
            ps.setString(3, apellidos);
            ps.setInt(4, idDestino);
            ps.executeUpdate();
        } catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
}
