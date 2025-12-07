package prototipo.proyectofinal;
import java.sql.*;
import java.time.LocalDateTime;

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
    
    //Constructores de Objetos
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
    
    //Funciones de residente
    public static void consultarVisitasPendientes(int idApto){
        int contador = 0; //Contador de Visitas
        String sql = "Select * from visitante where idDestino = ? and isAprobado = 0";//Comando SQL
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idApto); //Reemplazar ? por idApto
            ResultSet rs = ps.executeQuery(); //Guardar retorno
            while(rs.next()){ //Recorrer registros
                contador++;
                System.out.print(contador+") idVisitante: "+rs.getInt(2));
                System.out.print(" | Nombres: "+rs.getString(3));
                System.out.print(" | Apellidos: "+rs.getString(4)+" | \n");
            }
        } catch(Exception e){
            System.out.println("Error inesperado: "+e);
        }
    }
    
    public static void aprobarVisita(int idApto, int opcion){
        String sql = "Update visitante set isAprobado = 1 where idDestino = ? and idVisitante = ?";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idApto);
            ps.setInt(2, opcion);
            ps.executeUpdate();
        } catch (Exception e){
            System.out.println("El ID ingresado es invalido, vuelvalo a intentar");
        }
        System.out.print("Se aprobo la visita del visitante con ID: "+opcion);
    }
    
    public static void consultarVisitantes(int idDestino){
        String sql = "select * from visitante where idDestino = ? and isAprobado = 1";
        int contador = 0;
        try(Connection con = DriverManager.getConnection(url,user,password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idDestino);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                contador++;
                System.out.print(contador+" | iD: "+rs.getInt(2));
                System.out.print(" | Nombres: "+rs.getString(3));
                System.out.print(" | Apellidos: "+rs.getString(4));
                System.out.print(" | Fecha De Entrada: "+rs.getObject(7, LocalDateTime.class));
                System.out.print(" | Fecha de Salida: "+rs.getObject(8, LocalDateTime.class)+"\n");
            }
        } catch(Exception e){
            System.out.println("Algo Salio mal\n"+e);
        }
    }
    
    public static void consultarPaquetes(int idDestino){
        String sql = "select * from paquete where idDestino = ?";
        int contador = 0;
        try(Connection con = DriverManager.getConnection(url,user,password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idDestino);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                contador++;
                System.out.print(contador+" | Remitente: "+rs.getString(2));
                System.out.print(" | Destinatario: "+rs.getString(3));
                System.out.print(" | Entregado: "+rs.getBoolean(5));
                System.out.print(" | Fecha De Llegada: "+rs.getObject(7, LocalDateTime.class));
                if(rs.getBoolean(5)){
                    System.out.print(" | Fecha de Entrega: "+rs.getObject(8, LocalDateTime.class)+"\n");
                } else{
                    System.out.println("| No ha sido Entregado\n");
                }
            }
        } catch(Exception e){
            System.out.println("Algo Salio mal\n"+e);
        }
    }
}
