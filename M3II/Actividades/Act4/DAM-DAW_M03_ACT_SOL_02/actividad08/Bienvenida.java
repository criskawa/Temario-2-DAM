package actividad08;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Bienvenida extends JFrame {
	private JTextField field_nombre;
	/* private JTextField field_sueldo; */
	private JComboBox comboBox_edad;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private ButtonGroup bG_sexo;
	private JCheckBox chckbxProgramar;
	private JCheckBox chckbxDeporte;
	private JCheckBox chckbxCine;
	String separador = File.separator;
	String rutaProyecto = System.getProperty("user.dir");
	File archivo = new File(rutaProyecto + separador + "datos_empleados");

	public Bienvenida(String nombre) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblBienvenido = new JLabel("BIENVENIDO " + nombre);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(10, 10, 289, 14);
		getContentPane().add(lblBienvenido);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(88, 32, 46, 14);
		getContentPane().add(lblNombre);

		field_nombre = new JTextField();
		field_nombre.setBounds(160, 29, 179, 20);
		getContentPane().add(field_nombre);
		field_nombre.setColumns(10);


		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(88, 113, 46, 14);
		getContentPane().add(lblEdad);

		comboBox_edad = new JComboBox();
		comboBox_edad.setModel(new DefaultComboBoxModel(new String[] { "", "10-15", "16-20", "21-25", "26-30" }));
		comboBox_edad.setBounds(160, 110, 91, 20);
		getContentPane().add(comboBox_edad);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(88, 148, 46, 14);
		getContentPane().add(lblSexo);

		rdbtnHombre = new JRadioButton("Hombre");

		rdbtnHombre.setBounds(160, 144, 69, 23);
		getContentPane().add(rdbtnHombre);

		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(245, 144, 64, 23);
		getContentPane().add(rdbtnMujer);

		bG_sexo = new ButtonGroup();
		bG_sexo.add(rdbtnHombre);
		bG_sexo.add(rdbtnMujer);

		JLabel lblAficiones = new JLabel("Aficiones");
		lblAficiones.setBounds(88, 70, 59, 14);
		getContentPane().add(lblAficiones);

		chckbxProgramar = new JCheckBox("Programar");
		chckbxProgramar.setBounds(154, 70, 91, 23);
		getContentPane().add(chckbxProgramar);

		chckbxDeporte = new JCheckBox("Deporte");
		chckbxDeporte.setBounds(245, 70, 82, 23);
		getContentPane().add(chckbxDeporte);

		chckbxCine = new JCheckBox("Cine");
		chckbxCine.setBounds(324, 70, 59, 23);
		getContentPane().add(chckbxCine);

		JButton btnAceptar = new JButton("Nuevo Empleado");
		btnAceptar.addActionListener(new BtnAceptarActionListener());
		btnAceptar.setBounds(88, 180, 89, 23);
		getContentPane().add(btnAceptar);

		JButton btnMostrarEmpleados = new JButton("Mostrar Empleados");
		btnMostrarEmpleados.addActionListener(new BtnMostrarActionListener());
		btnMostrarEmpleados.setBounds(230, 180, 89, 23);
		getContentPane().add(btnMostrarEmpleados);

		setLocationRelativeTo(null);


		setSize(450, 300);
		setVisible(true);
		setLocationRelativeTo(null);
	}



	private class BtnAceptarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JDialog dialog = new JDialog();
			JLabel mensaje;
			ArrayList<Empleado> empleados_serializados = new ArrayList<Empleado>();
			if (field_nombre.getText().isEmpty() ||
		
					comboBox_edad.getSelectedItem().toString().length() == 0 || bG_sexo.getSelection() == null
					|| (!chckbxProgramar.isSelected() && !chckbxDeporte.isSelected() && !chckbxCine.isSelected())) {
				mensaje = new JLabel("Debes introducir todos los datos");
			} else {

				ObjectOutputStream oos;
				ObjectInputStream ois;
				try {
					if (archivo.exists()) {
						// LEER CONTENIDO DEL ARCHIVO PARA AÑADIR EL NUEVO EMPLEADO
						try {
							ois = new ObjectInputStream(new FileInputStream(archivo));
							empleados_serializados = (ArrayList<Empleado>) ois.readObject();
							ois.close();
						} catch (Exception errorLeer) {
							System.out.println("No se ha podido obtener el listado de empleados");
						}
					}

					HashMap<String, String> aficiones = new HashMap<String, String>();
					if (chckbxProgramar.isSelected()) {
						aficiones.put(chckbxProgramar.getText(), chckbxProgramar.getText());
					}
					if (chckbxDeporte.isSelected()) {
						aficiones.put(chckbxDeporte.getText(), chckbxDeporte.getText());
					}
					if (chckbxCine.isSelected()) {
						aficiones.put(chckbxCine.getText(), chckbxCine.getText());
					}
					String sexo;
					if(rdbtnHombre.isSelected()){
						sexo=rdbtnHombre.getText();
					}else{
						sexo=rdbtnMujer.getText();
					}

					Empleado nuevo_empleado = new Empleado(field_nombre.getText(),
							comboBox_edad.getSelectedItem().toString(),sexo, aficiones

					);
					System.out.println("Nuevo empleado:"+nuevo_empleado);
					empleados_serializados.add(nuevo_empleado);

					// ESCRIBIR EL ARCHIVO
					oos = new ObjectOutputStream(new FileOutputStream(archivo));

					oos.writeObject(empleados_serializados);
					oos.close();
					mensaje = new JLabel("Nuevo empleado almacenado");
				} catch (IOException e) {
					mensaje = new JLabel("Error al añadir el nuevo empleado");
					e.printStackTrace();
				}

			}
			mensaje.setBounds(88, 148, 46, 14);
			dialog.getContentPane().add(mensaje);
			dialog.setSize(300, 130);
			dialog.setVisible(true);
		}
	}

	private class BtnMostrarActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JLabel mensaje;
			JDialog dialog = new JDialog();
			// LEER EL ARCHIVO
			File archivo = new File(rutaProyecto + separador + "datos_empleados");
			ArrayList<Empleado> empresa2 = new ArrayList<Empleado>();
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new FileInputStream(archivo));

				empresa2 = (ArrayList<Empleado>) ois.readObject();
				ois.close();

				System.out.println("Hay un total de " + empresa2.size() + " empleados");
				String info_empleados = "";
				for (Empleado empleado_leido : empresa2) {
					info_empleados = info_empleados+"<br>-" + empleado_leido + "";
				}
				mensaje = new JLabel("<html> Empleados: " + info_empleados);
			} catch (Exception e1) {
				mensaje = new JLabel("No se han podido leer los empleados");
				e1.printStackTrace();
			}

			mensaje.setBounds(88, 148, 46, 14);
			dialog.getContentPane().add(mensaje);
			dialog.pack();
			dialog.setVisible(true);

		}
	}
}
