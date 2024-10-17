package modelo;


import java.util.UUID;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmDoctor;

public class Doctor {
    //1- Parametros
    String UUID_Doctor;
    String Nombre_Doctor;
    int Edad_Doctor;
    String Peso_Doctor;
    String Correo_Doctor;
    
    //2- Getters y Setters

    public String getUUID_Doctor() {
        return UUID_Doctor;
    }

    public void sertUUID_Doctor(String UUID_Doctor) {
        this.UUID_Doctor = UUID_Doctor;
    }

    public String getNombre_Doctor() {
        return Nombre_Doctor;
    }

    public void setNombre_Doctor(String Nombre_Doctor) {
        this.Nombre_Doctor = Nombre_Doctor;
    }

    public int getEdad_Doctor() {
        return Edad_Doctor;
    }

    public void setEdad_Doctor(int Edad_Doctor) {
        this.Edad_Doctor = Edad_Doctor;
    }

    public String getPeso_Doctor() {
        return Peso_Doctor;
    }

    public void setPeso_Doctor(String Peso_Doctor) {
        this.Peso_Doctor = Peso_Doctor;
    }
    
        public String getCorreo_Doctor() {
        return Correo_Doctor;
    }
        
       public void setCorreo_Doctor(String Correo_Doctor) {
        this.Correo_Doctor = Correo_Doctor;
    }
    
    //3- Funciones (Guardar, mostrar, eliminar, actualizar)
   
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addDoctor = conexion.prepareStatement("INSERT INTO tbDoctor(UUID_Doctor, Nombre_Doctor, Edad_Doctor, Peso_Doctor, Correo_Doctor) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addDoctor.setString(1, UUID.randomUUID().toString());
            addDoctor.setString(2, getNombre_Doctor());
            addDoctor.setInt(3, getEdad_Doctor());
            addDoctor.setString(4, getPeso_Doctor());
            addDoctor.setString(5, getCorreo_Doctor());
 
            //Ejecutar la consulta
            addDoctor.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloPinulito = new DefaultTableModel();
        modeloPinulito.setColumnIdentifiers(new Object[]{UUID_Doctor, Nombre_Doctor, Edad_Doctor, Peso_Doctor, Correo_Doctor});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbDoctor");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloPinulito.addRow(new Object[]{rs.getString("UUID_Doctor"), 
                    rs.getString("Nombre_Doctor"), 
                    rs.getInt("Edad_Doctor"), 
                    rs.getString("Peso_Doctor"),
                    rs.getString("Correo_Doctor")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloPinulito);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
       public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbDoctor where UUID_Doctor = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
       
       public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbDoctor set Nombre_Doctor= ?, Edad_Doctor = ?, Peso_Doctor = ?, Correo_Doctor = ? where UUID_Doctor = ?");
                updateUser.setString(1, getNombre_Doctor());
                updateUser.setInt(2, getEdad_Doctor());
                updateUser.setString(3, getPeso_Doctor());
                updateUser.setString(4, getCorreo_Doctor());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no funciona actualizar");
        }
    }
       
        public void cargarDatosTabla(frmDoctor vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbDoctor.getSelectedRow();
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbDoctor.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbDoctor.getValueAt(filaSeleccionada, 1).toString();
            String EdadTB = vista.jtbDoctor.getValueAt(filaSeleccionada, 2).toString();
            String PesoTB = vista.jtbDoctor.getValueAt(filaSeleccionada, 3).toString();
            String CorreoTB = vista.jtbDoctor.getValueAt(filaSeleccionada, 4).toString();
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadTB);
            vista.txtPeso.setText(PesoTB);
            vista.txtCorreo.setText(CorreoTB);
        }                
    }
}

/*
CREATE TABLE tbDoctor (
UUID_Doctor VARCHAR2(100),
Nombre_Doctor VARCHAR2(100),
Edad_Doctor INT,
Peso_Doctor NUMBER(2,5),
Correo_Doctor VARCHAR2(100)
);

DROP TABLE tbDoctor
        */