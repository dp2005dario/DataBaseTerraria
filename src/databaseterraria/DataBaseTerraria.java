
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseterraria;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;


public class DataBaseTerraria {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/terraria";

    public static void main(String[] args) throws SQLException {
        final String usuario = "root";
        final String password = "1234";

        // Aqui encontramos todas las inserciones de cada tablas de la base de datos
        insertarMundo(usuario, password, 1, "'Blostelandia'", "'Normal'", 5);
        insertarEnemigo(usuario, password, 10, "'zombi'", 100, 20, 1);
        insertarNPC(usuario, password, 2, "'carpintero'", "'Lucas'", "'alta'", 1);
        insertarBioma(usuario, password, "'jungla'", "'jungleRuin'", 200, "'alta'", 1);
        insertarPersonaje(usuario, password, 20, "'Lorenzo'", "'alta'", 20, LocalDate.of(2025, 10, 10), 100, 100, 1);
        insertarArma(usuario, password, 2000, "'Lanzagranadas'", 10, 10, 350, "'Legendaria'", 20);
        insertarArmadura(usuario, password, 8000, 300, 400, "'Mitico'", "'Velocidad'", 20);
        insertarEquipable(usuario, password, 56000, "'Salto Propulsado'", "'Curacion cada 20 segundos'", 20);
        insertarInvocador(usuario, password, "'Pequeslime'", 20, 20);
        insertarMago(usuario, password, "'Barita magica'", "'Elevado'", 20);
        insertarMelee(usuario, password, "'TerraSword'", 300, 20);
        insertarRanger(usuario, password, "'Bloste'", 1200, 20);
        insertarEnemigosHasPersonaje(usuario, password, 10, 20); 
        
        //Por aqui tenemos todas los update que le haremos a las tablas
        actualizarMundo(usuario, password, "'Blostelandia'");
        actualizarEnemigo(usuario, password, "'zombi'");
        actualizarNPC(usuario, password, "'Lucas'");
        actualizarBioma(usuario, password, "'jungleRuin'");
        actualizarPersonaje(usuario, password, "'Dario'");
        actualizarArma(usuario, password, "'Legendaria'");
        actualizarArmadura(usuario, password, "'Mitico'");
        actualizarEquipable(usuario, password, "'Salto Propulsado'");
        actualizarInvocador(usuario, password, 20); //DAR ERROR ARREGLAR
        actualizarMago(usuario, password, "'Elevado'");
        actualizarMelee(usuario, password, 300);
        actualizarRanger(usuario, password, 1200);

        
        //Econtramos todos los delete que haremos a cada tabla sobre las insercciones que hemos realizado, hacer antes un insert y update
        eliminarMundo(usuario, password, 1); //error
        eliminarEnemigo(usuario, password, 10); //error
        eliminarNPC(usuario, password, 2);
        eliminarBioma(usuario, password, 200);
        eliminarPersonaje(usuario, password, 20); //error
        eliminarArma(usuario, password, 350);
        eliminarArmadura(usuario, password, 8000);
        eliminarEquipable(usuario, password, 56000);
        eliminarInvocador(usuario, password, 20);
        eliminarMago(usuario, password, "'Elevado'");
        eliminarMelee(usuario, password, "'TerraSword'");
        eliminarRanger(usuario, password, "'Bloste'");
        eliminarEnemigosHasPersonaje(usuario, password, 10);  
        
        
        //Aqui tenemos las consultas que vamos a realizar
                consultas(usuario,password);
    }
    
    
    
    
    
       private static void consultas(String usuario, String password) {
    try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
         Statement statement = conn.createStatement()) {

        Class.forName(DRIVER);
                System.out.println("Conectado correctamente a la base de datos.");

        String selectTableSQL = "SELECT nombre,edad FROM PERSONAJE WHERE nombre LIKE 'L%'";
        ResultSet rs = statement.executeQuery(selectTableSQL); // executeQuery para SELECT
        boolean hayResultados=false;
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            int edad = rs.getInt("edad"); // si edad es INT en la DB
            System.out.println("nombre : " + nombre);
            System.out.println("edad   : " + edad);
        }
         if (!hayResultados) {
            System.out.println("No se encontraron registros que coincidan con 'L%'.");
        }

    } catch (SQLException | ClassNotFoundException e) {
        System.out.println(e.getMessage());
    }
}
    
    
    
    
    
    
    
    
    
    
    

    // ------------------- MUNDO -------------------
    private static void insertarMundo(String usuario, String password, int id, String nombre, String dificultad, int nivelcorrupcion) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO MUNDO(id,nombre,dificultad,nivelcorrupcion) VALUES(" + id + ", " + nombre + ", " + dificultad + ", " + nivelcorrupcion + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert mundo: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarMundo(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE MUNDO SET nombre = 'BlosteWorld' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update mundo: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarMundo(String usuario, String password, int id) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM MUNDO WHERE id=1" + id;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete mundo: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- ENEMIGOS -------------------
    private static void insertarEnemigo(String usuario, String password, int id, String nombre, int vida, int damage, int mundoId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO ENEMIGOS(id,nombre,vida,damage,MUNDO_id) VALUES(" + id + ", " + nombre + ", " + vida + ", " + damage + ", " + mundoId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert enemigos: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarEnemigo(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE ENEMIGOS SET nombre = 'Demogorgon' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update enemigos: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarEnemigo(String usuario, String password, int id) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM ENEMIGOS WHERE id =10" + id;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete enemigos: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- NPCS -------------------
    private static void insertarNPC(String usuario, String password, int id, String oficio, String nombre, String felicidad, int mundoId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO NPCS(id,oficio,nombre,felicidad,MUNDO_id) VALUES(" + id + ", " + oficio + ", " + nombre + ", " + felicidad + "," + mundoId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert npcs: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarNPC(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE NPCS SET nombre = 'francisco' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update npcs: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarNPC(String usuario, String password, int id) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM NPCS WHERE id = " + id;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete npcs: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- BIOMA -------------------
    private static void insertarBioma(String usuario, String password, String paisaje, String nombre, int profundidad, String peligrosidad, int mundoId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO BIOMA(paisaje,nombre,profundidad,peligrosidad,MUNDO_id) VALUES(" + paisaje + ", " + nombre + ", " + profundidad + ", " + peligrosidad + "," + mundoId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert bioma: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarBioma(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE BIOMA SET nombre = 'JungleCastle' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update bioma: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarBioma(String usuario, String password, int profundidad) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM BIOMA WHERE profundidad= " + profundidad;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete bioma: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- PERSONAJE -------------------
    private static void insertarPersonaje(String usuario, String password, int id, String nombre, String dificultad, int edad, LocalDate fechaCreacion, int dineroInicial, int vida, int mundoId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String fechaSQL = "'" + fechaCreacion.toString() + "'";
            String insertSQL = "INSERT INTO PERSONAJE(id,nombre,dificultad,edad,fechacreacion,dineroinicial,vida,MUNDO_id) VALUES(" + id + "," + nombre + ", " + dificultad + ", " + edad + "," + fechaSQL + "," + dineroInicial + "," + vida + "," + mundoId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarPersonaje(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE PERSONAJE SET nombre = 'dp2005dario' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarPersonaje(String usuario, String password, int id) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM PERSONAJE WHERE id=20" + id;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- ARMAS -------------------
    private static void insertarArma(String usuario, String password, int etiqueta, String nombre, int damage, int tiempoUso, int precio, String calidad, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO ARMAS(etiqueta,nombre,damage,tiempouso,precio,calidad,PERSONAJE_id) VALUES(" + etiqueta + "," + nombre + "," + damage + ", " + tiempoUso + ", " + precio + "," + calidad + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert armas: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarArma(String usuario, String password, String calidad) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE ARMAS SET calidad = 'Raro' WHERE calidad = " + calidad;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update armas: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarArma(String usuario, String password, int precio) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM ARMAS WHERE precio= " + precio;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete armas: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- ARMADURAS -------------------
    private static void insertarArmadura(String usuario, String password, int etiqueta, int defensa, int precio, String calidad, String bonus, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO ARMADURAS(etiqueta,defensa,precio,calidad,bonusarmadura,PERSONAJE_id) VALUES(" + etiqueta + "," + defensa + "," + precio + ", " + calidad + ", " + bonus + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert armaduras: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarArmadura(String usuario, String password, String calidad) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE ARMADURAS SET calidad = 'Raro' WHERE calidad = " + calidad;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update armaduras: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarArmadura(String usuario, String password, int etiqueta) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM ARMADURAS WHERE etiqueta= " + etiqueta;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete armaduras: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- EQUIPABLES -------------------
    private static void insertarEquipable(String usuario, String password, int etiqueta, String beneficio1, String beneficio2, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO EQUIPABLES(etiqueta,beneficio1,beneficio2,PERSONAJE_id) VALUES(" + etiqueta + "," + beneficio1 + "," + beneficio2 + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert equipables: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarEquipable(String usuario, String password, String beneficio1) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE EQUIPABLES SET beneficio1 = 'Propulsado' WHERE beneficio1 = " + beneficio1;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update equipables: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarEquipable(String usuario, String password, int etiqueta) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM EQUIPABLES WHERE etiqueta= " + etiqueta;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete equipables: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- INVOCADOR -------------------
    private static void insertarInvocador(String usuario, String password, String armapreferida, int personajeId, int numeroinvocaciones) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO INVOCADOR(armapreferida,numeroinvocaciones,PERSONAJE_id) VALUES(" + armapreferida + "," + personajeId + "," + numeroinvocaciones + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert invocador: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarInvocador(String usuario, String password, int numeroinvocaciones) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE INVOCADOR SET numeroinvocaciones=300 WHERE PERSONAJE_id = " + numeroinvocaciones;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update invocador: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarInvocador(String usuario, String password, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM INVOCADOR WHERE PERSONAJE_id= " + personajeId;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete invocador: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- MAGO -------------------
    private static void insertarMago(String usuario, String password, String armapreferida, String daño, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO MAGO(armapreferida,usomana,PERSONAJE_id) VALUES(" + armapreferida + "," + daño + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert mago: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarMago(String usuario, String password, String daño) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE MAGO SET usomana = 'Moderado' WHERE usomana = " + daño;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update mago: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarMago(String usuario, String password, String daño) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM MAGO WHERE usomana= " + daño;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete mago: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- MELEE -------------------
    private static void insertarMelee(String usuario, String password, String armapreferida, int damage, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO MELEE(armapreferida,criticaldamage,PERSONAJE_id) VALUES(" + armapreferida + "," + damage + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert melee: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarMelee(String usuario, String password, int damage) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE MELEE SET criticaldamage = 150 WHERE criticaldamage = " + damage;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update melee: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarMelee(String usuario, String password, String armapreferida) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM MELEE WHERE armapreferida= " + armapreferida;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete melee: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- RANGER -------------------
    private static void insertarRanger(String usuario, String password, String armapreferida, int alcance, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO RANGER(armapreferida,alcance,PERSONAJE_id) VALUES(" + armapreferida + "," + alcance + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert ranger: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void actualizarRanger(String usuario, String password, int damage) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE RANGER SET alcance = 1400 WHERE alcance = " + damage;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update ranger: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarRanger(String usuario, String password, String armapreferida) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM RANGER WHERE armapreferida= " + armapreferida;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete ranger: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- ENEMIGOS_HAS_PERSONAJE -------------------
    private static void insertarEnemigosHasPersonaje(String usuario, String password, int enemigosId, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO ENEMIGOS_HAS_PERSONAJE(ENEMIGOS_id,PERSONAJE_id) VALUES(" + enemigosId + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert enemigos_has_personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    private static void eliminarEnemigosHasPersonaje(String usuario, String password, int enemigosId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM ENEMIGOS_HAS_PERSONAJE WHERE ENEMIGOS_id= " + enemigosId;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete enemigos_has_personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

}

