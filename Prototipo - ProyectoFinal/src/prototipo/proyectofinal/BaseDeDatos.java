package prototipo.proyectofinal;
import java.sql.*;

public class BaseDeDatos {
    private static String url = "jdbc:mysql://localhost:3306/conjunto";
    private static String user = "root";
    private static String password = "Jj10302526";
    
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
            resultado = (contraseñaBD.equals(contraseña)&rolBD==rol);
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
}
