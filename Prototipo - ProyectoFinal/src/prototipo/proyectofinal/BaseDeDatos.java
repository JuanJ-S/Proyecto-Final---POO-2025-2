package prototipo.proyectofinal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public static List<List<String>> consultarVisitasPendientes(int idApto){
        List<List<String>> visitasPendientes = new ArrayList<>(); //Lista de Retorno
        List<String> registro = new ArrayList<>(); //Guardara cada Registro devuelto
        int contador = 0; //Contador de Visitas
        String sql = "Select * from visitante where idDestino = ? and isAprobado = 0";//Comando SQL
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){ //Conexion y preCarga del comando
            ps.setInt(1, idApto); //Reemplazar ? por idApto
            ResultSet rs = ps.executeQuery(); //Guardar retorno del mySql
            while(rs.next()){ //Recorrer registros
                contador++;
                //Guardamos el Registro en el siguente orden: Contador,IDVisitante,Nombres,Apellidos
                registro.add(String.valueOf(contador));
                registro.add(String.valueOf(rs.getInt(2)));
                registro.add(rs.getString(3));
                registro.add(rs.getString(4));
                visitasPendientes.add(registro); //Guardamos el registro en la lista de retorno
                registro.clear(); //Limpiamos registro para el siguiente bucle
            }
        } catch(Exception e){
            System.out.println("Error inesperado: "+e);
        }
        return visitasPendientes;
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
    
    public static List<List<String>> consultarVisitantes(int idDestino, boolean isAprobado){
        List<List<String>> consulta = new ArrayList<>(); //Variable de Retorno
        List<String> registro = new ArrayList<>();
        String sql = "select * from visitante where idDestino = ? and isAprobado = ?";
        int contador = 0;
        try(Connection con = DriverManager.getConnection(url,user,password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idDestino);
            ps.setBoolean(2, isAprobado);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                contador++;
                //Guardamos los valores de registro en el siguiente orden: ID, Nombres, Apellidos, Fecha de Entrada, Fecha de Salida
                registro.add(String.valueOf(contador));
                registro.add(String.valueOf(rs.getInt(2)));
                registro.add(rs.getString(3));
                registro.add(rs.getString(4));
                registro.add(String.valueOf(rs.getObject(7, LocalDateTime.class)));
                registro.add(String.valueOf(rs.getObject(8, LocalDateTime.class)));
                consulta.add(registro); //Guardamos el registro en consulta
                registro.clear();
            }
        } catch(Exception e){
            System.out.println("Algo Salio mal\n"+e);
        }
        return consulta;
    }
    
    public static List<List<String>> consultarPaquetes(int idDestino){
        List<List<String>> paquetes = new ArrayList<>(); //Lsita de Retorno
        List<String> registro = new ArrayList<>(); //Variable de Registros
        String sql = "select * from paquete where idDestino = ?"; //Comando sql
        int contador = 0;
        try(Connection con = DriverManager.getConnection(url,user,password); //Conectar con BD
        PreparedStatement ps = con.prepareStatement(sql);){//Preparar comando
            ps.setInt(1, idDestino); //Reemplazar ?
            ResultSet rs = ps.executeQuery(); //Guardar Retorno
            while(rs.next()){ //Recorrer Retorno - Guardar en el orden: Remitente, Destinatario, isEntregado, FechaLlegada, FechaEntrega
                contador++;
                registro.add(rs.getString(2));
                registro.add(rs.getString(3));
                registro.add(String.valueOf(rs.getBoolean(5)));
                registro.add(String.valueOf(rs.getObject(7, LocalDateTime.class)));
                registro.add(String.valueOf(rs.getObject(8, LocalDateTime.class)));
                paquetes.add(registro); //Agregar registro a paquete
                registro.clear(); //Limppiar registro para el siguiente buccle
            }
        } catch(Exception e){
            System.out.println("Algo Salio mal\n"+e);
        }
        return paquetes;
    }
    
    //Métodos de Admin
    public static int idApto(int torre, int numero){
        int idApto = 0;
        String sql = "select * from apto where torre = ? and numero = ?";
        try(Connection con = DriverManager.getConnection(url,user,password);
         PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, torre);
            ps.setInt(2, numero);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                idApto = rs.getInt(1);
            }
        } catch(Exception e){
            System.out.println("El apartamento no existe o hay un error en el programa");
        }
        return idApto;
    }
    
    public static List<List<String>> consultarVisitantes(int idDestino){
        List<List<String>> visitas = new ArrayList<>(); //Variable de Retorno
        List<String> registro = new ArrayList<>(); //Guardado de Registros
        int contador = 0;
        String sql = "Select * from visitante where idDestino = ?"; //Comando sql
        try(Connection con = DriverManager.getConnection(url,user,password);//Conectar a BD
        PreparedStatement ps = con.prepareStatement(sql);){//Preparar comando
            ps.setInt(1, idDestino);//Reemplazar ?
            ResultSet rs = ps.executeQuery();//Guardar Retorno
            while(rs.next()){ //Recorrer Registro - Se guardan datos en el orden: idVisitante, Nombres, Apellidos, Fecha de Entrada, Fecha de Salida
                contador++;
                registro.add(String.valueOf(rs.getInt(2)));
                registro.add(rs.getString(3));
                registro.add(rs.getString(4));
                registro.add(String.valueOf(rs.getObject(7, LocalDateTime.class)));
                registro.add(String.valueOf(rs.getObject(8, LocalDateTime.class)));
                visitas.add(registro); //Guardar registro en visitas
                registro.clear(); //Limpiar registro para el siguiente bucle
            }
        } catch(Exception e){
            System.out.println("El apto ingresado no tiene visitas");
        }
        return visitas;
    }
    
    public static List<String> consultarApto(int idApto){
        List<String> apto = new ArrayList<>(); //Variable de Retorno
        String sql = "Select * from apto where idApto = ?"; //Comando sql
        try(Connection con = DriverManager.getConnection(url,user,password); //Conectar con BD
        PreparedStatement ps = con.prepareStatement(sql);){ //Preparar Comando
            ps.setInt(1, idApto); //Reemplazar ?
            ResultSet rs = ps.executeQuery(); //Guardar Retorno
            while(rs.next()){ //Recorrer Retorno - Orden de Datos: ID, Torre, Numero, Propietario, Id Propietario, isArrendado
                apto.add(String.valueOf(rs.getInt(1)));
                apto.add(String.valueOf(rs.getInt(2)));
                apto.add(String.valueOf(rs.getInt(3)));
                apto.add(rs.getString(4));
                apto.add(String.valueOf(rs.getInt(5)));
                apto.add(String.valueOf(rs.getBoolean(6)));
            }
        } catch(Exception e){
            System.out.println("El apto ingresado no exite");
        }
        return apto;
    }
    
    public static List<String> consultarResidente(int idApto){
        List<String> residente = new ArrayList<>(); //Variable de Retorno
        String sql = "Select * from residente where idApto = ?"; //Comando sql
        try(Connection con = DriverManager.getConnection(url,user,password); //Conectar con BD
        PreparedStatement ps = con.prepareStatement(sql);){//Preparar comando
            ps.setInt(1, idApto); //Reemplazar ?
            ResultSet rs = ps.executeQuery();//Guardar Retorno
            while(rs.next()){//Recorrer retorno - Orden: Nombre, Apellidos, IdResidente
                residente.add(rs.getString(1));
                residente.add(rs.getString(2));
                residente.add(String.valueOf(rs.getInt(3)));
            }
        } catch(Exception e){
            System.out.println("El apto ingresado no exite");
        }
        return residente;
    }
    
    public static String registrarApto(int torre, int numero, String propietario, int idPropietario, boolean isArrendado){
        String confirmacion = "";
        String sql = "insert into apto values(null,?,?,?,?,?)";
        try(Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, torre);
            ps.setInt(2, numero);
            ps.setString(3, propietario);
            ps.setInt(4, idPropietario);
            ps.setBoolean(5, isArrendado);
            ps.executeUpdate();
            confirmacion += "Se registro el apartamento correctamente";
        } catch(Exception e){
            confirmacion += "Error al registrar vuelve a intentarlo"+e;
        }
        return confirmacion;
    }
    public static void registrarUsuario(String nombres, String apellidos, int rol, String contraseña){
        String sql = "insert into usuario values(?,?,?,null,?)"; //Comando
        try(Connection con = DriverManager.getConnection(url, user, password); //Conectar al BD
        PreparedStatement ps = con.prepareStatement(sql);){ //Preparar comando
            ps.setString(1, nombres); //Reemplazar ?
            ps.setString(2, apellidos);
            ps.setInt(3, rol);
            ps.setString(4, contraseña);
            ps.executeUpdate(); //Ejecutar Comando
            System.out.println("Se registro al residente Exitosamente"); //Mensaje de Confirmacion
        } catch(Exception e){
            System.out.println("Error al registrar residente: "+e);
        }
    }
}
