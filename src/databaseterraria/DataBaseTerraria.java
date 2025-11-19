/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package databaseterraria;

  import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Darío
 */
public class DataBaseTerraria {
        /**
        *Definimos e inicializamos el driver 
        *DRIVER. Nombre del driver que vamos a utilizar. 
        **/
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
           /**
        *Definimos e inicializamos aqui la url 
        * donde tenemos localizado nuestra base de datos en mi caso en el puerto 3306
        **/
        private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/terraria";
         /**
        *Aqui encontramos el main donde vamos a realizar todo el proceso de conexion
        **/
        public static void main(String args[]) throws SQLException {
            //Declaracion de el nombre del usuario y su contrasena para poder meternos en la base de datos
            //Punto 2 acabado habria que distribuirlos bien que yo me encargaré con la interfaz
            final String usuario = "root";
            final String password = "1234";
            //Aqui encontramos objetos del JDBC que nos servirá praa hacer las sentecnias, el connection para conectarnos y el statement para las sentencias
             Connection dbConnection = null;
             Statement statement = null;
             Statement statement2 = null;
            //Vamos a declarar las disstitnas variables para cada columna de la tabla MUNDOS
                int id =1;
                String nombre = "'Blostelandia'";
                String dificultad ="'Normal'";
                int nivelcorrupcion=5;
                //Llamaremos al metodo de la insercion,actualizacion y eliminacion
                gestionarMundo(URL_CONEXION, usuario, password, 1, "'Blostelandia'", "'Normal'",5);
            
           //Declaramos variable de enemigos
            id=10;
            nombre="'zombi'";
             int vida=100;
             int damage=20;
             int MUNDO_id=62039;
             gestionarEnemigos(URL_CONEXION,usuario,password,10,"'zombi'",100,20,62039);
             
             
             //Declaramos variable de npcs
             id=2;
             String oficio="'carpintero'";
             nombre="'Lucas'";
             String felicidad="'alta'";
             MUNDO_id=62039;
             gestionarNPCS(URL_CONEXION,usuario,password,2,"'carpintero'","'Lucas'","'alta'",62039);
             
             //Declaramos variable de bioma
             String paisaje="'jungla'";
             nombre="'jungleRuin'";
             int profundidad=200;
             String peligrosidad="'alta'";
             MUNDO_id=62039;
              gestionarBIOMAS(URL_CONEXION,usuario,password,"'jungla'","'jungleRuin'",200,"'alta'",62039);


             //Declaramos variable personaje
             id=20;
             nombre="'Dario'";
             dificultad="'alta'";
             int edad=20;
             LocalDate fechacreacion=LocalDate.of(2025, 10, 10);
             int dineroinicial=100;
             vida=100;
             MUNDO_id=62039;
             gestionarPersonajes(URL_CONEXION,usuario,password,20,"'Dario'","'alta'",20,fechacreacion,100,100,62039);

            //Declaramos variable armas
            int etiqueta=2000;
            nombre="'Lanzagranadas'";
            damage=10;
            int tiempouso=10;
            int precio=350;
            String calidad="'Legendaria'";
            int PERSONAJE_id=18092006;
            gestionarArmas(URL_CONEXION,usuario,password,2000,"'Lanzagranadas'",10,10,350,"'Legendaria'",18092006);
            
            //Declaramos variable armaduras
            etiqueta=8000;
            int defensa=300;
            precio=400;
            calidad="'Mitico'";
            String bonusarmadura="'Velocidad'";
            PERSONAJE_id=18092006;
            gestionarArmaduras(URL_CONEXION,usuario,password,8000,300,400,"'Mitico'","'Velocidad'",18092006);
            

            //Declaramos variable equipables
             etiqueta=56000;
             String beneficio1="'Salto Propulsado'";
             String beneficio2="''";
             PERSONAJE_id=18092006;
             gestionarEquipables(URL_CONEXION,usuario,password,56000,"'Salto Propulsado'","'Curacion cada 20 segundos'",18092006);

               //Declaramos variable invocador
             String armapreferida="'Pequeslime'";
             int numeroinvocaciones=20;
             PERSONAJE_id=18092006;
             gestionarInvocador(URL_CONEXION,usuario,password,"'Pequeslime'",20,18092006);
             
              //Declaramos variable mago
             armapreferida="'Barita magica'";
             String  usomana="'Elevado'";
             PERSONAJE_id=18092006;
             gestionarMago(URL_CONEXION,usuario,password,"'Barita magica'","'Elevado'",18092006);
             
              //Declaramos variable melee
             armapreferida="'TerraSword'";
             int criticaldamage=300;
             PERSONAJE_id=18092006;
             gestionarMelee(URL_CONEXION,usuario,password,"'TerraSword'",300,18092006);

             //Declaramos variable Ranger
             armapreferida="'Bloste'";
             int alcance=1200;
             PERSONAJE_id=18092006;
             gestionarRanger(URL_CONEXION,usuario,password,"'Bloste'",1200,18092006);
             
             //Declaramos variable en la tabla compuesta por Personaje y Enemigo
              int ENEMIGOS_id=12345;  //Debemos modificarlo junto a  todos los personajes_id
             PERSONAJE_id=18092006;
             gestionarenemigos_has_personajes(URL_CONEXION,usuario,password,12345,18092006);




            


             
                
                
        }
                //Aqui tenemos el metodo de insert,update y delete de gestionarEnemigos
            public static void gestionarEnemigos(String url,String usuario,String password,int id,String nombre,int vida,int damage,int MUNDO_id){
                Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla Enemigos
                String insertTableSQL = "INSERT INTO ENEMIGOS(id,nombre,vida,damage,MUNDO_id) VALUES("+ id +", "+ nombre +", "+vida+", "+damage+", "+MUNDO_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert enemigos: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion del nombre Blostelandia a BlosteWorld             
                String updateTableSQL = "UPDATE ENEMIGOS SET nombre = 'Demogorgon' WHERE nombre = " + nombre;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update enemigos: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro, borraremos el ya creado mundo
                String deleteTableSQL = "DELETE FROM ENEMIGOS WHERE id = 10";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete enemigos: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
            }
        
        
        
        
        
        
        
        
            //Aqui tenemos el metodo de insert,update y delete de gestionarMundo
            public static void gestionarMundo(String url,String usuario,String password,int id,String nombre,String dificultad,int nivelcorrupcion){
                Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla Enemigos
                String insertTableSQL = "INSERT INTO MUNDO(id,nombre,dificultad,nivelcorrupcion) VALUES("+ id +", "+ nombre +", "+dificultad+", "+nivelcorrupcion+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert mundo: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion del nombre Blostelandia a BlosteWorld             
                String updateTableSQL = "UPDATE MUNDO SET nombre = 'BlosteWorld' WHERE nombre = " + nombre;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update mundo: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro, borraremos el ya creado mundo
                String deleteTableSQL = "DELETE FROM MUNDO WHERE id = 1";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete mundo: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
            }
                        //Aqui tenemos el metodo de insert,update y delete de gestionarNPCS
    private static void gestionarNPCS(String URL_CONEXION, String usuario, String password, int id, String oficio, String nombre, String felecidad, int MUNDO_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO NPCS(id,oficio,nombre,felicidad,MUNDO_id) VALUES("+ id +", "+ oficio+", "+nombre+", "+felecidad+","+MUNDO_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert npcs: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion del nombre            
                String updateTableSQL = "UPDATE NPCS SET nombre = 'francisco' WHERE nombre = " + nombre;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update npcs: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro, borraremos el ya creado mundo
                String deleteTableSQL = "DELETE FROM NPCS WHERE id = 2";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete npcs: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
    
    
    
    
                           //Aqui tenemos el metodo de insert,update y delete de gestionarBIOMAS
    private static void gestionarBIOMAS(String URL_CONEXION, String usuario, String password, String paisaje, String nombre,int profundidad, String peligrosidad, int MUNDO_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO BIOMA(paisaje,nombre,profundidad,peligrosidad,MUNDO_id) VALUES("+ paisaje+", "+ nombre+", "+profundidad+", "+peligrosidad+","+MUNDO_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert bioma: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion del nombre            
                String updateTableSQL = "UPDATE BIOMA SET nombre = 'JungleCastle' WHERE nombre = " + nombre;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update bioma: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro, borraremos el ya creado mundo
                String deleteTableSQL = "DELETE FROM BIOMA WHERE profundidad= 200";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete bioma: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
    
    
                    //Aqui tenemos el metodo de insert,update y delete de gestionarPersonajes
           private static void gestionarPersonajes(String URL_CONEXION, String usuario, String password,int id,String nombre, String dificultad,int edad,LocalDate fechacreacion,int dineroinicial,int vida,int MUNDO_id) {
          Connection dbConnection = null;
                Statement statement = null;
                //Aqui pasamos la fecha de creacion a un String para que nos la lea mucho mejor
                String fechaSQL = "'" + fechacreacion.toString() + "'";
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO PERSONAJE(id,nombre,dificultad,edad,fechacreacion,dineroinicial,vida,MUNDO_id) VALUES("+id+","+nombre+", "+dificultad+", "+edad+","+fechaSQL+","+dineroinicial+","+vida+","+MUNDO_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert personaje: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion del nombre            
                String updateTableSQL = "UPDATE PERSONAJE SET nombre = 'dp2005dario' WHERE nombre = " + nombre;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update personaje: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro, borraremos el ya creado mundo
                String deleteTableSQL = "DELETE FROM PERSONAJE WHERE id= 20";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete personaje: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
           
           
           
               
                    //Aqui tenemos el metodo de insert,update y delete de gestionarArmas
           private static void gestionarArmas(String URL_CONEXION, String usuario, String password,int etiqueta,String nombre,int damage, int tiempouso,int precio,String calidad,int PERSONAJE_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO ARMAS(etiqueta,nombre,damage,tiempouso,precio,calidad,PERSONAJE_id) VALUES("+etiqueta+","+nombre+","+damage+", "+tiempouso+", "+precio+","+calidad+","+PERSONAJE_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert armas: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion             
                String updateTableSQL = "UPDATE ARMAS SET calidad = 'Raro' WHERE calidad = " + calidad;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update armas: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro
                String deleteTableSQL = "DELETE FROM ARMAS WHERE precio= 350";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete armas: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }

                        //Aqui tenemos el metodo de insert,update y delete de gestionarArmaduras
           private static void gestionarArmaduras(String URL_CONEXION, String usuario, String password,int etiqueta,int defensa,int precio,String calidad,String bonusarmadura,int PERSONAJE_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO ARMADURAS(etiqueta,defensa,precio,calidad,bonusarmadura,PERSONAJE_id) VALUES("+etiqueta+","+defensa+","+precio+", "+calidad+", "+bonusarmadura+","+PERSONAJE_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert armaduras: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion             
                String updateTableSQL = "UPDATE ARMADURAS SET calidad = 'Raro' WHERE calidad = " + calidad;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update armaduras: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro
                String deleteTableSQL = "DELETE FROM ARMADURAS WHERE etiqueta= 8000";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete armaduras: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
           
           
                                   //Aqui tenemos el metodo de insert,update y delete de gestionarEquipables
           private static void gestionarEquipables(String URL_CONEXION, String usuario, String password,int etiqueta,String beneficio1,String beneficio2,int PERSONAJE_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO EQUIPABLES(etiqueta,beneficio1,beneficio2,PERSONAJE_id) VALUES("+etiqueta+","+beneficio1+","+beneficio2+","+PERSONAJE_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert equipables: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion             
                String updateTableSQL = "UPDATE EQUIPABLES SET beneficio1 = 'Deslizamiento sobre hielo' WHERE beneficio1 = " + beneficio1;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update equipables: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro
                String deleteTableSQL = "DELETE FROM EQUIPABLES WHERE etiqueta= 56000";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete equipables: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
           
           
           
               //Aqui tenemos el metodo de insert,update y delete de gestionarInvocador
           private static void gestionarInvocador(String URL_CONEXION, String usuario, String password,String armapreferida,int numeroinvocaciones,int PERSONAJE_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO INVOCADOR(armapreferida,numeroinvocaciones,PERSONAJE_id) VALUES("+armapreferida+","+numeroinvocaciones+","+PERSONAJE_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert invocador: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion             
                String updateTableSQL = "UPDATE INVOCADOR SET numeroinvocaciones = 30 WHERE numeroinvocaciones = " + numeroinvocaciones;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update invocador: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro
                String deleteTableSQL = "DELETE FROM INVOCADOR WHERE numeroinvocaciones= 30";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete invocador: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
           
           
           
                 //Aqui tenemos el metodo de insert,update y delete de gestionarMago
           private static void gestionarMago(String URL_CONEXION, String usuario, String password,String armapreferida,String usomana,int PERSONAJE_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO MAGO(armapreferida,usomana,PERSONAJE_id) VALUES("+armapreferida+","+usomana+","+PERSONAJE_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert mago: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion             
                String updateTableSQL = "UPDATE MAGO SET usomana='Elevado' WHERE usomana = " + usomana;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update mago: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro
                String deleteTableSQL = "DELETE FROM MAGO WHERE usomana= 'Elevado'";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete mago: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
           
           
                   //Aqui tenemos el metodo de insert,update y delete de gestionarMelee
           private static void gestionarMelee(String URL_CONEXION, String usuario, String password,String armapreferida,int criticaldamage,int PERSONAJE_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO MELEE(armapreferida,criticaldamage,PERSONAJE_id) VALUES("+armapreferida+","+criticaldamage+","+PERSONAJE_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert melee: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion             
                String updateTableSQL = "UPDATE MELEE SET criticaldamage=400 WHERE criticaldamage = " + criticaldamage;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update melee: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro
                String deleteTableSQL = "DELETE FROM MELEE WHERE armapreferida= 'TerraSword'";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete  melee: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
                      
                   //Aqui tenemos el metodo de insert,update y delete de gestionarRanger
           private static void gestionarRanger(String URL_CONEXION, String usuario, String password,String armapreferida,int alcance,int PERSONAJE_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO RANGER(armapreferida,alcance,PERSONAJE_id) VALUES("+armapreferida+","+alcance+","+PERSONAJE_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert Ranger: "+ res);

                //Aqui realizaremos con el updateTableSQL la actualizacion             
                String updateTableSQL = "UPDATE RANGER SET alcance=1400 WHERE alcance = " + alcance;
                
                 //Aqui nos sale el numero de columnas que se le han hecho el update
                int res2 = statement.executeUpdate(updateTableSQL);
                System.out.println("Numero de registros afectados en update Ranger: "+ res2);
                
                //Aqui tenemos la sentencia de borrar un registro
                String deleteTableSQL = "DELETE FROM RANGER WHERE armapreferida= 'Bloste'";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete  Ranger: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
           
           //Aqui tenemos el metodo de insert,update y delete de la tabla compuesta de enemigos y personajes
           private static void gestionarenemigos_has_personajes(String URL_CONEXION, String usuario, String password,int ENEMIGOS_id,int PERSONAJE_id) {
          Connection dbConnection = null;
                Statement statement = null;
                       try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Esta clase DriverManager sirve para gestionar todos los drivers que tennemos y para conectarnos usaremos,la variable ruta, el usuario y la contrasena para la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Y esto hara que se conecte este obJEto statement y nos permitirá ejecutar las sentencias
                statement = conn.createStatement();
                
                //Con esto podemos  insertar registros en nuestras tablas SQL, en este caso veremos la inserción de  un enemigo correspondiente a la tabla NPCS
                String insertTableSQL = "INSERT INTO enemigos_has_personaje(ENEMIGOS_id,PERSONAJE_id) VALUES("+ENEMIGOS_id+","+PERSONAJE_id+");";
               
                
                
                //Y aqui con el executeUpdate nos saldrá als sentencias que se han actualizado
                int res = statement.executeUpdate(insertTableSQL);
                System.out.println("Numero de registros afectados en insert tabla compuesta: "+ res);

                
                //Aqui tenemos la sentencia de borrar un registro
                String deleteTableSQL = "DELETE FROM enemigos_has_personaje WHERE ENEMIGOS_id= 12345";
                
               //Y por ultimo tenemos lo que saldra por consola de filas afectadas con la tabla delete
                int res3 = statement.executeUpdate(deleteTableSQL);
                System.out.println("Numero de registros afectados en delete  tabla compuesta: "+ res3);
             
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                if (statement != null) {
                    statement.close();
                    }
                if (dbConnection != null) {
                    dbConnection.close();
                    }
                    } catch (SQLException ex) {
                    System.out.println("Error al cerrar recursos: " + ex.getMessage());
                    }
                }
    }
            
    }   


