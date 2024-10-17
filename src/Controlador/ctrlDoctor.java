package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import modelo.Doctor;
import vista.frmDoctor;

public class ctrlDoctor implements MouseListener{
   
    //1- Llamar a las otras capas(modelo, vista)
    private frmDoctor vista;
    private Doctor modelo;
    
    //2- Crear el constructor de la clase
    public ctrlDoctor(frmDoctor Vista, Doctor Modelo){
        this.vista = Vista;
        this.modelo = Modelo;
        
        vista.btnGuardar.addMouseListener(this); 
        
        //Para mostrar los datos
        vista.jtbDoctor.addMouseListener(this);
        modelo.Mostrar(vista.jtbDoctor);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.jtbDoctor.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
      if(e.getSource() == vista.btnGuardar){
          modelo.setNombre_Doctor(vista.txtNombre.getText());
          modelo.setEdad_Doctor(Integer.parseInt(vista.txtEdad.getText()));
          modelo.setPeso_Doctor(vista.txtPeso.getText());
          modelo.setCorreo_Doctor(vista.txtCorreo.getText());
          
          modelo.Guardar(); 
          modelo.Mostrar(vista.jtbDoctor);
      }
      
      if(e.getSource() == vista.btnEliminar){
          modelo.Eliminar(vista.jtbDoctor);
          modelo.Mostrar(vista.jtbDoctor);
      }
      
      if(e.getSource() == vista.jtbDoctor){
          modelo.cargarDatosTabla(vista);
      }
      
      if(e.getSource() == vista.btnActualizar){
          modelo.setNombre_Doctor(vista.txtNombre.getText());
          modelo.setEdad_Doctor(Integer.parseInt(vista.txtEdad.getText()));
          modelo.setPeso_Doctor(vista.txtPeso.getText());
          modelo.setCorreo_Doctor(vista.txtCorreo.getText());
          
          modelo.Actualizar(vista.jtbDoctor);
          modelo.Mostrar(vista.jtbDoctor);
      }
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
