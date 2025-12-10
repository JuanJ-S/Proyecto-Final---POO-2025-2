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
        // Corregido: Agregar filtro para visitas activas (sin salida)
        String sql = "SELECT idVisitante, nombres, apellidos, fechaEntrada FROM visitante WHERE idDestino = ? AND isAprobado = false AND fechaSalida IS NULL;";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Usar índices correctos (1: idVisitante, 2: nombres, 3: apellidos, 4: fechaDeEntrada)
                resultado.append("ID: ").append(rs.getInt("idVisitante"))
                        .append(", Nombre: ").append(rs.getString("nombres")).append(" ").append(rs.getString(3))
                        .append(", Entrada: ").append(rs.getTimestamp("fechaEntrada")).append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }

    public static void aprobarVisita(int idApto, int idVisitante) {
        String sql = "UPDATE visitante SET isAprobado = true, fechaEntrada = now() WHERE idVisitante = ? AND idDestino = ?";
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
        String sql = "SELECT idVisitante, nombres, apellidos, fechaEntrada, fechasalida FROM visitante WHERE idDestino = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("idVisitante"))
                        .append(", Nombre: ").append(rs.getString("nombres")).append(" ").append(rs.getString("apellidos"))
                        .append(", Entrada: ").append(rs.getTimestamp("fechaEntrada"))
                        .append(", Salida: ").append(rs.getTimestamp("fechasalida") != null ? rs.getTimestamp("fechasalida") : "Pendiente").append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }

    public static String consultarPaquetes(int idApto) {
        StringBuilder resultado = new StringBuilder();
        String sql = "SELECT idSistema, remitente, fechaLlegada, isEntregado FROM paquete WHERE idDestino = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("idSistema"))
                        .append(", Remitente: ").append(rs.getString("remitente"))
                        .append(", Llegada: ").append(rs.getTimestamp("fechaLlegada"))
                        .append(", Entregado: ").append(rs.getBoolean("isEntregado") ? "Sí" : "No").append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }

    public static String consultarPaquetesPorDia(int idApto, String dia) {
        StringBuilder resultado = new StringBuilder();
        String sql = "SELECT idSistema, remitente, fechaLlegada, isEntregado FROM paquete WHERE idDestino = ? AND DATE(fechaLlegada) = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ps.setString(2, dia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("idSistema"))
                        .append(", Remitente: ").append(rs.getString("remitente"))
                        .append(", Llegada: ").append(rs.getTimestamp("fechaLlegada"))
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
    
    public static void registrarPaquete(String remitente, String destinatario, int idDestino){
        String sql = "insert into paquete values(null,?,?,?,false,now(),null)";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1, remitente);
            ps.setString(2, destinatario);
            ps.setInt(3, idDestino);
            ps.executeUpdate();
        } catch(Exception e){
            System.out.println("Error: "+e);
        }
    }

    public static int obtenerIdApto(int torre, int numero){
        int idApto = 0;
        String sql = "select idApto from apto where torre = ? and numero = ?";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, torre);
            ps.setInt(2, numero);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                idApto = rs.getInt(1);
            }
        } catch(Exception e){
            System.out.println("Error: "+e);
        }
        return idApto;
    }
    public static void registrarSalida(int idVisitante){
        String sql = "update visitante set fechaSalida = now() where idVisitante = ?";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idVisitante);
            ps.executeUpdate();
        } catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    public static void registrarEntrega(int idPaquete){
        String sql = "update paquete set fechaEntrega = now(), isEntregado = true where idSistema = ?";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idPaquete);
            ps.executeUpdate();
        } catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    //Funciones para Administrador
    public static String consultarApto(int torre, int numero) {
        StringBuilder resultado = new StringBuilder();
        String sql = "select * from apto where torre = ? and numero = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, torre);
            ps.setInt(2, numero);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("ID Apartamento: ").append(rs.getInt(1))
                        .append(", Torre: ").append(rs.getInt(2))
                        .append(", Número: ").append(rs.getInt(3))
                        .append(", Propietario: ").append(rs.getString(4))
                        .append(", ID Propietario: ").append(rs.getInt(5))
                        .append(", Esta Arrendado: ").append(rs.getBoolean(6) ? "Si" : "No").append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }
    
    public static String consultarResidente(int idApto) {
        StringBuilder resultado = new StringBuilder();
        String sql = "select * from residente where idApto = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.append("Nombres: ").append(rs.getString(1))
                        .append(", Apellidos: ").append(rs.getString(2))
                        .append(", ID Residente: ").append(rs.getInt(3))
                        .append(", ID Apartamento: ").append(rs.getInt(4)).append("\n");
            }
        } catch (Exception e) {
            resultado.append("Error: ").append(e.getMessage());
        }
        return resultado.toString();
    }
    
    public static void registrarApto(int torre, int numero, String propietario, int idPropietario, boolean isArrendado){
        String sql = "insert into apto values(null,?,?,?,?,?)";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, torre);
            ps.setInt(2, numero);
            ps.setString(3, propietario);
            ps.setInt(4, idPropietario);
            ps.setBoolean(5, isArrendado);
            ps.executeUpdate();
        } catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public static void registrarResidente(String nombres, String apellidos, int idResidente, int idApto, int idSesion){
        String sql = "insert into residente values(?,?,?,?,?)";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setInt(3, idResidente);
            ps.setInt(4, idApto);
            ps.setInt(5, idSesion);
            ps.executeUpdate();
        } catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
}
