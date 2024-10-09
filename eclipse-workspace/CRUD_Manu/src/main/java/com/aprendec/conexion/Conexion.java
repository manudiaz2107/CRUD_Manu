package com.aprendec.conexion;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Clase que gestiona la conexión a la base de datos utilizando un 
 * pool de conexiones administrado por Apache Commons DBCP.
 * 
 * Esta clase se asegura de que solo haya una instancia de 
 * BasicDataSource a lo largo de la aplicación, y proporciona 
 * métodos para obtener conexiones a la base de datos.
 */
public class Conexion {
    
    private static BasicDataSource dataSource = null;

    /**
     * Obtiene la instancia de DataSource, creando una nueva si es necesario.
     * 
     * @return DataSource la fuente de datos configurada.
     */
    private static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setUrl("jdbc:mariadb://localhost:3306/crud_manu?useTimezone=true&serverTimezone=UTC");
            dataSource.setInitialSize(20);
            dataSource.setMaxIdle(15);
            dataSource.setMaxTotal(20);
            dataSource.setMaxWaitMillis(10000);
        }
        return dataSource;
    }

    /**
     * Obtiene una conexión a la base de datos.
     * 
     * @return Connection la conexión a la base de datos.
     * @throws SQLException si ocurre un error al obtener la conexión.
     */
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
