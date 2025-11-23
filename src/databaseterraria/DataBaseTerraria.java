
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

/**
 * La clase que abarca la propia base de datos
 * Realiza la conexión, inserta actualiza y borra registros predeterminados
*/
public class DataBaseTerraria {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/terraria";
    private Connection sesionactual;

    /**
     * El método main que usamos
     * @param args
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        final String usuario = "root";
        final String password = "1234";
        
        
        Connection sesionActual = iniciarSesion(usuario, password);
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
        actualizarPersonaje(usuario, password, "'Lorenzo'");
        actualizarArma(usuario, password, "'Legendaria'");
        actualizarArmadura(usuario, password, "'Mitico'");
        actualizarEquipable(usuario, password, "'Salto Propulsado'");
        actualizarInvocador(usuario, password, 20); 
        actualizarMago(usuario, password, "'Elevado'");
        actualizarMelee(usuario, password, 300);
        actualizarRanger(usuario, password, 1200);

        
        //Aqui tenemos las consultas que vamos a realizar
                consultas(usuario,password);
        
        
        //Econtramos todos los delete que haremos a cada tabla sobre las insercciones que hemos realizado, hacer antes un insert y update
        eliminarNPC(usuario, password, 2);
        eliminarBioma(usuario, password, 200);
        eliminarArma(usuario, password, 350);
        eliminarArmadura(usuario, password, 8000);
        eliminarEquipable(usuario, password, 56000);
        eliminarInvocador(usuario, password, 20);
        eliminarMago(usuario, password, "'Moderado'");
        eliminarMelee(usuario, password, "'TerraSword'");
        eliminarRanger(usuario, password, "'Bloste'");
        eliminarEnemigosHasPersonaje(usuario, password, 10);
        eliminarEnemigo(usuario, password, 10); 
        eliminarPersonaje(usuario, password, "'Leonardo'"); 
        eliminarMundo(usuario, password, 1); 
        
        //Y para finalizar, con este metodo cuando la conexion sea null o queramos cerrar sesion nos saldrá
        cerrarSesion(sesionActual);
        sesionActual=null;
    }
    
    /**
     * Funcion para iniciar sesion, al recibir los parametros de usuario y contraseña los compara a los de la Base de Datos
     * En caso de fallar, devuelve un mensaje de error, si los datos son correctos se realiza la conexion
    */
    public static Connection iniciarSesion(String usuario, String password) {
    try {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
        System.out.println("Conexion a BBD correcta");
        return conn;  // devolvemos la conexión ya abierta
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println("Error con la conexion a BBD: " + e.getMessage());
        return null;
    }
}
    
    
    /**
     * Metodo que va realizando consultas predeterminadas que usan LIKE, JOIN y GROUP BY, tiene un catchException en caso de haber error
    */
      private static void consultas(String usuario, String password) {
    try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password)) {

        Class.forName(DRIVER);
        System.out.println("Conectado correctamente a la base de datos.");

        // Primera consulta con LIKE
                 System.out.println("    ----- LIKE -------  ");
        String selectTableSQL = "SELECT nombre, edad FROM PERSONAJE WHERE nombre LIKE 'L%'";
        try (Statement st1 = conn.createStatement();
             ResultSet rs1 = st1.executeQuery(selectTableSQL)) {

            boolean hay1 = false;

            while (rs1.next()) {
                hay1 = true;
                System.out.println("nombre : " + rs1.getString("nombre"));
                System.out.println("edad   : " + rs1.getInt("edad"));
            }

            if (!hay1) {
                System.out.println("No se encontraron registros que coincidan con 'L%'.");
            }
        }

        // Segunda consulta con LIKE
                         System.out.println("    ----- LIKE -------  ");
        selectTableSQL = "SELECT nombre FROM ENEMIGOS WHERE nombre LIKE 'D%'";
        try (Statement st2 = conn.createStatement();
             ResultSet rs2 = st2.executeQuery(selectTableSQL)) {

            boolean hay2 = false;

            while (rs2.next()) {
                hay2 = true;
                System.out.println("nombre : " + rs2.getString("nombre"));
            }

            if (!hay2) {
                System.out.println("No se encontraron registros que coincidan con 'D%'.");
            }
        }
        
        // Tercera consulta con LIKE
                         System.out.println("    ----- LIKE -------  ");
        selectTableSQL = "SELECT id,oficio,nombre FROM NPCS WHERE nombre LIKE 'f%'";
        try (Statement st3 = conn.createStatement();
             ResultSet rs3 = st3.executeQuery(selectTableSQL)) {

            boolean hay3 = false;

            while (rs3.next()) {
                hay3 = true;
                System.out.println("id   : " + rs3.getInt("id"));
                System.out.println("nombre : " + rs3.getString("nombre"));
                System.out.println("oficio : " + rs3.getString("oficio"));
            }

            if (!hay3) {
                System.out.println("No se encontraron registros que coincidan con 'f%'.");
            }
        }

            // Primera consulta JOIN 
         System.out.println("    ----- JOIN -------  ");
         String joinTableSQL = "SELECT p.nombre AS personaje_nombre,p.edad,m.nombre AS mundo_nombre FROM personaje p JOIN mundo m ON(p.Mundo_id=m.id);";
        try (Statement st4 = conn.createStatement();
             ResultSet rs4 = st4.executeQuery(joinTableSQL)) {

            boolean hay4 = false;

            while (rs4.next()) {
                hay4 = true;
                System.out.println("p.nombre   : " + rs4.getString("personaje_nombre"));
                System.out.println("p.edad : " + rs4.getInt("edad"));
                System.out.println("m.nombre : " + rs4.getString("mundo_nombre"));
            }

            if (!hay4) {
                System.out.println("Incompatibilidad");
            }
        }
        
        
           // Segunda consulta JOIN 
         System.out.println("    ----- JOIN -------  ");
         joinTableSQL = "SELECT b.paisaje,m.nombre FROM bioma b JOIN mundo m ON(b.Mundo_id=m.id);";
        try (Statement st5 = conn.createStatement();
             ResultSet rs5 = st5.executeQuery(joinTableSQL)) {

            boolean hay5 = false;

            while (rs5.next()) {
                hay5 = true;
                System.out.println("b.paisaje   : " + rs5.getString("paisaje"));
                System.out.println("m.nombre : " + rs5.getString("nombre"));
            }

            if (!hay5) {
                System.out.println("Incompatibilidad");
            }
        }

              // Tercera consulta JOIN 
         System.out.println("    ----- JOIN -------  ");
         joinTableSQL = "SELECT n.oficio,n.nombre AS nombre_npc,m.nombre AS nombre_mundo FROM NPCS n JOIN mundo m ON(n.Mundo_id=m.id);";
        try (Statement st6 = conn.createStatement();
             ResultSet rs6 = st6.executeQuery(joinTableSQL)) {

            boolean hay6 = false;

            while (rs6.next()) {
                hay6 = true;
                System.out.println("n.oficio   : " + rs6.getString("oficio"));
                System.out.println("n.nombre : " + rs6.getString("nombre_npc"));
                System.out.println("m.nombre : " + rs6.getString("nombre_mundo"));
            }

            if (!hay6) {
                System.out.println("Incompatibilidad");
            }
        }
        
        
                // Segunda consulta Group By 
         System.out.println("    ----- group By -------  ");
         System.out.println(" agrupa por dificultad el total de personajes ");
         selectTableSQL = "SELECT dificultad, COUNT(*) AS total_personajes FROM personaje GROUP BY dificultad;";
        try (Statement st7 = conn.createStatement();
             ResultSet rs7 = st7.executeQuery(selectTableSQL)) {

            boolean hay7 = false;
            while (rs7.next()) {
                hay7 = true;
                System.out.println("dificultad   : " + rs7.getString("total_personajes"));
            }

            if (!hay7) {
                System.out.println("Incompatibilidad");
            }
        }
        
        
                // Segunda consulta Group By 
         System.out.println("    ----- group By -------  ");
         System.out.println("es el promedio de vida de los enemigos");
         selectTableSQL = "SELECT id, AVG(vida) AS promedio_vida FROM enemigos GROUP BY id;";
        try (Statement st8 = conn.createStatement();
             ResultSet rs8 = st8.executeQuery(selectTableSQL)) {

            boolean hay8 = false;
            while (rs8.next()) {
                hay8 = true;
                System.out.println("ENEMIGOS_id   : " + rs8.getString("id"));
                System.out.println("vida_promedio   : " + rs8.getString("promedio_vida"));
            }

            if (!hay8) {
                System.out.println("Incompatibilidad");
            }
        }
        
        
        
                // Tercera consulta Group By 
         System.out.println("    ----- group By -------  ");
         System.out.println("suma la edades de todos los personajes por dificultad");
         selectTableSQL = "SELECT dificultad, SUM(edad) AS total_edad FROM personaje GROUP BY dificultad;";
        try (Statement st9 = conn.createStatement();
             ResultSet rs9 = st9.executeQuery(selectTableSQL)) {

            boolean hay9 = false;
            while (rs9.next()) {
                hay9 = true;
                System.out.println("dificultad  : " + rs9.getString("dificultad"));
                System.out.println("edad_total   : " + rs9.getString("total_edad"));
            }
            
            if (!hay9) {
                System.out.println("Incompatibilidad");
            }
        }
     //Aqui tenemos en caso de error lo que saltaria
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println(e.getMessage());
    }

    
    
    
    
    
      }
    // ------------------- MUNDO -------------------
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Mundo los datos que recibe (usuario, contraseña, id, nombre, dificultad y nivelcorrupcion
    */
    private static void insertarMundo(String usuario, String password, int id, String nombre, String dificultad, int nivelcorrupcion) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO MUNDO(id,nombre,dificultad,nivelcorrupcion) VALUES(" + id + ", " + nombre + ", " + dificultad + ", " + nivelcorrupcion + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert mundo: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }
    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Mundo el nombre de las lineas afectadas a BlosteWorld
    */
    private static void actualizarMundo(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE MUNDO SET nombre = 'BlosteWorld' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update mundo: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Mundo las filas afectadas
    */
    private static void eliminarMundo(String usuario, String password, int id) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM MUNDO WHERE id = " + id;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete mundo: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- ENEMIGOS -------------------
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Enemigo los datos que recibe (usuario, contraseña, id, nombre, vida,  damage y mundoId
    */
    private static void insertarEnemigo(String usuario, String password, int id, String nombre, int vida, int damage, int mundoId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO ENEMIGOS(id,nombre,vida,damage,MUNDO_id) VALUES(" + id + ", " + nombre + ", " + vida + ", " + damage + ", " + mundoId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert enemigos: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Enemigo las tablas afectadas a Demogorgon
    */
    private static void actualizarEnemigo(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE ENEMIGOS SET nombre ='Demogorgon' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update enemigos: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Enemigo las filas afectadas
    */
    private static void eliminarEnemigo(String usuario, String password, int id) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM ENEMIGOS WHERE id = " + id;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete enemigos: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- NPCS -------------------
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla NPC los datos que recibe (usuario, contraseña, id, oficio, nombre,  felicidad y mundoId
    */
    private static void insertarNPC(String usuario, String password, int id, String oficio, String nombre, String felicidad, int mundoId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO NPCS(id,oficio,nombre,felicidad,MUNDO_id) VALUES(" + id + ", " + oficio + ", " + nombre + ", " + felicidad + "," + mundoId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert npcs: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla NPC los nombres afectados a francisco
    */
    private static void actualizarNPC(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE NPCS SET nombre = 'francisco' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update npcs: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     + Comprueba la conexion con la base de datos, si funciona, elimina en la tabla NPC las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Bioma los datos que recibe (usuario, contraseña, paisaje, nombre, profundidad,  peligrosidad y mundoId
    */
    private static void insertarBioma(String usuario, String password, String paisaje, String nombre, int profundidad, String peligrosidad, int mundoId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO BIOMA(paisaje,nombre,profundidad,peligrosidad,MUNDO_id) VALUES(" + paisaje + ", " + nombre + ", " + profundidad + ", " + peligrosidad + "," + mundoId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert bioma: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Bioma los nombres afectados a JungleCastle
    */
    private static void actualizarBioma(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE BIOMA SET nombre = 'JungleCastle' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update bioma: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Bioma las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Personaje los datos que recibe (usuario, contraseña, id, nombre, dificultad,  edad, fechaCreacion, dineroInicial, vida y mundoId
    */
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

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Personaje los nombres afectados a leonardo
    */
    private static void actualizarPersonaje(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE PERSONAJE SET nombre ='Leonardo' WHERE nombre = " + nombre;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Personaje las filas afectadas
    */
    private static void eliminarPersonaje(String usuario, String password, String nombre) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM PERSONAJE WHERE nombre = " + nombre;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    // ------------------- ARMAS -------------------
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Arma los datos que recibe (usuario, contraseña, etiqueta, nombre, damage, tiempoUso, precio, calidad y personajeId
    */
    private static void insertarArma(String usuario, String password, int etiqueta, String nombre, int damage, int tiempoUso, int precio, String calidad, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO ARMAS(etiqueta,nombre,damage,tiempouso,precio,calidad,PERSONAJE_id) VALUES(" + etiqueta + "," + nombre + "," + damage + ", " + tiempoUso + ", " + precio + "," + calidad + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert armas: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Arma las calidades afectadas a raro
    */
    private static void actualizarArma(String usuario, String password, String calidad) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE ARMAS SET calidad = 'Raro' WHERE calidad = " + calidad;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update armas: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Arma las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Armadura los datos que recibe (usuario, contraseña, etiqueta, defensa, precio, calidad, bonus y personajeId
    */
    private static void insertarArmadura(String usuario, String password, int etiqueta, int defensa, int precio, String calidad, String bonus, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO ARMADURAS(etiqueta,defensa,precio,calidad,bonusarmadura,PERSONAJE_id) VALUES(" + etiqueta + "," + defensa + "," + precio + ", " + calidad + ", " + bonus + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert armaduras: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Armadura las calidades afectadas a raro
    */
    private static void actualizarArmadura(String usuario, String password, String calidad) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE ARMADURAS SET calidad = 'Raro' WHERE calidad = " + calidad;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update armaduras: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Armadura las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Equipable los datos que recibe (usuario, contraseña, etiqueta, beneficio1, beneficio2 y personajeId
    */
    private static void insertarEquipable(String usuario, String password, int etiqueta, String beneficio1, String beneficio2, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO EQUIPABLES(etiqueta,beneficio1,beneficio2,PERSONAJE_id) VALUES(" + etiqueta + "," + beneficio1 + "," + beneficio2 + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert equipables: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Equipable los beneficio1 afectados a Propulsado
    */
    private static void actualizarEquipable(String usuario, String password, String beneficio1) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE EQUIPABLES SET beneficio1 = 'Propulsado' WHERE beneficio1 = " + beneficio1;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update equipables: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Equipable las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Invocador los datos que recibe (usuario, contraseña, armapreferida, personajeId y numeroinvocaciones
    */
    private static void insertarInvocador(String usuario, String password, String armapreferida, int personajeId, int numeroinvocaciones) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO INVOCADOR(armapreferida,numeroinvocaciones,PERSONAJE_id) VALUES(" + armapreferida + "," + personajeId + "," + numeroinvocaciones + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert invocador: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Invocador los numeroinvocaciones afectados a 300
    */
    private static void actualizarInvocador(String usuario, String password, int numeroinvocaciones) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE INVOCADOR SET numeroinvocaciones=300 WHERE PERSONAJE_id = " + numeroinvocaciones;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update invocador: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Invocador las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Invocador los datos que recibe (usuario, contraseña, armapreferida, personajeId y daño
    */
    private static void insertarMago(String usuario, String password, String armapreferida, String daño, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO MAGO(armapreferida,usomana,PERSONAJE_id) VALUES(" + armapreferida + "," + daño + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert mago: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Mago los usomana afectados a moderado
    */
    private static void actualizarMago(String usuario, String password, String daño) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE MAGO SET usomana ='Moderado' WHERE usomana = " + daño;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update mago: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina  en la tabla Mago las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Melee los datos que recibe (usuario, contraseña, armapreferida, personajeId y damage
    */
    private static void insertarMelee(String usuario, String password, String armapreferida, int damage, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO MELEE(armapreferida,criticaldamage,PERSONAJE_id) VALUES(" + armapreferida + "," + damage + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert melee: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Melee los damage afectados a 150
    */
    private static void actualizarMelee(String usuario, String password, int damage) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE MELEE SET criticaldamage = 150 WHERE criticaldamage = " + damage;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update melee: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Melee las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla Ranger los datos que recibe (usuario, contraseña, armapreferida, personajeId y alcance
    */
    private static void insertarRanger(String usuario, String password, String armapreferida, int alcance, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO RANGER(armapreferida,alcance,PERSONAJE_id) VALUES(" + armapreferida + "," + alcance + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert ranger: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, actualiza en la tabla Ranger los alcance afectadosa 1400
    */
    private static void actualizarRanger(String usuario, String password, int damage) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String updateSQL = "UPDATE RANGER SET alcance = 1400 WHERE alcance = " + damage;
            int res = statement.executeUpdate(updateSQL);
            System.out.println("Numero de registros afectados en update ranger: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla Ranger las filas afectadas
    */
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
    /**
     * Comprueba la conexion con la base de datos, si funciona, inserta en la tabla EnemigosHasPersonaje los datos que recibe (usuario, contraseña, enemigosId y personajeId
    */
    private static void insertarEnemigosHasPersonaje(String usuario, String password, int enemigosId, int personajeId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String insertSQL = "INSERT INTO ENEMIGOS_HAS_PERSONAJE(ENEMIGOS_id,PERSONAJE_id) VALUES(" + enemigosId + "," + personajeId + ");";
            int res = statement.executeUpdate(insertSQL);
            System.out.println("Numero de registros afectados en insert enemigos_has_personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Comprueba la conexion con la base de datos, si funciona, elimina en la tabla EnemigosHasPersonaje las filas afectadas
    */
    private static void eliminarEnemigosHasPersonaje(String usuario, String password, int enemigosId) {
        try (Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
             Statement statement = conn.createStatement()) {

            Class.forName(DRIVER);
            String deleteSQL = "DELETE FROM ENEMIGOS_HAS_PERSONAJE WHERE ENEMIGOS_id= " + enemigosId;
            int res = statement.executeUpdate(deleteSQL);
            System.out.println("Numero de registros afectados en delete enemigos_has_personaje: " + res);

        } catch (SQLException | ClassNotFoundException e) { System.out.println(e.getMessage()); }
    }
    
    /**
     * Metodo para cerrar sesion en la base de datos, usa SQLException si no se puede hacer
    */
    public static void cerrarSesion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Sesión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar sesión: " + e.getMessage());
            }
        }
    }

    }

