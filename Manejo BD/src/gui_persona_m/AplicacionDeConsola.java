package gui_persona_m;

import java.io.*;
import java.sql.*;

public class AplicacionDeConsola {  //aqui va el nombre de la clase.
	//atributos
	Conexion conexion=new Conexion();
	
	public static void main(String[] args) throws Exception
	{
		//variables locales
		//creacion de instancia                 para mandar a llamar a los métodos.
		//de la clase
		AplicacionDeConsola obj=new AplicacionDeConsola();
		int op=0;
		
		InputStreamReader consola= new InputStreamReader(System.in);
		BufferedReader teclado= new BufferedReader(consola);
		
		//menú
			do {
				System.out.println("Menu de BD agenda Tabla Personas");
				System.out.println("1.-Alta");
				System.out.println("2.-Consultas Generales");
				System.out.println("3.-Baja");
				System.out.println("4.-Actualizar");
				System.out.println("5.-Consultas especifica");
				System.out.println("6.-Salir");
				System.out.println("Digita tu opcion:");
			
				//lecturas de datos del teclado opcion del menu
				op=Integer.parseInt(teclado.readLine());
				switch (op)
			{
				case 1:
					System.out.println("Altas");
					obj.altaPersona();
					break;
				case 2:
					System.out.println("Consultas Generales");
					obj.consultaGeneralPersona();
					break;
				case 3:
					System.out.println("Baja");
					obj.bajaPersona();
					break;
				case 4:
					System.out.println("Modificar");
					obj.modificarPersona();
					break;
				case 5:
					System.out.println("Consulta especifica de persona");
					obj.consultaEspecificaPersona();
					break;
				case 6:
					System.out.println("Bye");
					break;
				default:
					System.out.println("Opcion incorrecta!!!");
		} ///switch
	  }while (op!=6);
	} 
	
	//metodo altaPersona
	public void altaPersona() throws Exception
	{
		InputStreamReader consola= new InputStreamReader (System.in);
		BufferedReader teclado= new BufferedReader (consola);
        String sql;

        String nombre;
        String edad ;
        String sexo;

        	System.out.println("Digita el nombre");
        	nombre=teclado.readLine();
        	System.out.println("Digita la edad");
        	edad=teclado.readLine ();
        	System.out.println("Digita el sexo");
        	sexo=teclado.readLine();
        	sql="insert into persona (nombre, edad, sexo)"
        			+ " values ('"+nombre+"',"+edad+",'"+sexo+"');";
        	System. out.println (sql);
        	conexion.conectar();
        	conexion.escribir(sql);
        	conexion.Off(); 
		}
		//metodo consulta general persona
	public void consultaGeneralPersona () throws Exception
	{
		//variables locales
		String sql;
		ResultSet respuesta;
		//campos
		int id;
		String nombre;
		int edad;
		String sexo;
		
		conexion.conectar();
		//consulta general a la tabla persona
		sql="select * from persona";
		System.out.println(sql);
		respuesta=conexion.obtenerDatos(sql);
		
		System.out.println("\nID  NOMBRE \t EDAD  \t\t SEXO \n");
		while(respuesta.next())
		{
			id=respuesta.getInt("id");
			nombre=respuesta.getString("nombre");
			edad=respuesta.getInt("edad");
			sexo=respuesta.getString("sexo");
			System.out.println(id+ "   "+nombre+" \t "+edad+" \t\t "+sexo );
			
		}//while
		System.out.println();
		conexion.Off();
	}
	//metodo bajaPersona
	public void bajaPersona() throws Exception
	{
		//variables locales
		int id;
		String sql;
		
		//para leer la consola
		InputStreamReader consola=new InputStreamReader (System.in);
		BufferedReader teclado=new BufferedReader(consola);
		
		System.out.println("Digita el id del registro a eliminar:");
		id=Integer.parseInt(teclado.readLine());
		sql="delete from persona where id="+id;
		
		conexion.conectar();
		System.out.println(sql);
		conexion.Exe(sql);
		conexion.Off();
	}
	//metodo modificarPersona
	public void modificarPersona() throws Exception
	{
		int op=0;
		int id, edad;
		String nombre, sexo, sql="";
		
		InputStreamReader consola=new InputStreamReader(System.in);
		BufferedReader teclado=new BufferedReader(consola);
		
		System.out.println("Menu de modificar");
		System.out.println("1.-Modificar nombre");
		System.out.println("2.-Modificar edad");
		System.out.println("3.-Modificar sexo");
		System.out.println("4.-Modificar todo");
		System.out.println("5.-Digita tu opcion");
		//lectura de datos del teclado
		op=Integer.parseInt(teclado.readLine());
		
		System.out.println("Digita el id:");
		id=Integer.parseInt(teclado.readLine());
		switch(op) 
			{
		case 1:
			System.out.println("Modificar nombre");
			System.out.println("Digita el nombre:");
			nombre=teclado.readLine();
			sql="update persona set nombre= '"+nombre+"' where id= "+id;
			break;
		case 2:
			System.out.println("Modificar edad");
			System.out.println("Digita la edad:");
			edad=Integer.parseInt(teclado.readLine());
			sql="update persona set edad=" +edad+ " where id=" +id;
			break;
		case 3:
			System.out.println("Modificar sexo");
			System.out.println("Digita el sexo:");
			sexo=teclado.readLine();
			sql="update persona set sexo='"+sexo+"' where id="+id;
			break;
		case 4:
			System.out.println("Modificar todo");
			System.out.println("Digita el nombre:");
			nombre=teclado.readLine();
			System.out.println("Digita la edad:");
			edad=Integer.parseInt(teclado.readLine());
			System.out.println("Digita el sexo:");
			sexo=teclado.readLine();
			sql="update persona"
				+" set nombre='"+nombre+"' ,"
				+" edad="+edad+","
				+"sexo='"+sexo+"'"
				+" where id="+id;
			break;
		default:
			System.out.println("Opcion incorrecta!!!");
			} //switch
		System.out.println(sql);
		conexion.conectar();
		conexion.actualizar(sql);
		conexion.Off();
	}
	//metodo consultaEspecificaPersona
	public void consultaEspecificaPersona() throws Exception
	{
		int op=0;
		int id, edad;
		String nombre, sexo, sql="";
		ResultSet resp;
		
		InputStreamReader consola=new InputStreamReader(System.in);
		BufferedReader teclado=new BufferedReader(consola);
		
		System.out.println("Menu de consultas especificas de personas");
		System.out.println("1.-Consultar nombre");
		System.out.println("2.-Consultar edad");
		System.out.println("3.-Consultar sexo");
		System.out.println("Digita tu opcion:");
		//lectura de datos del teclado
		op=Integer.parseInt(teclado.readLine());
		
		switch(op)
		      {
		    	  case 1:
		    		  System.out.println("Consultar por nombre");
		    		  System.out.println("Digita el nombre:");
		    		  nombre=teclado.readLine();
		    		  sql="select * from persona where nombre like '%"+nombre+"%'";
		    		  break;
		    	  case 2:
		    		  System.out.println("Consulta por edad");
		    		  System.out.println("Digite la edad:");
		    		  edad=Integer.parseInt(teclado.readLine());
		    		  sql="select * from persona where edad='"+edad+"'";
		    		  break;
		    	  case 3:
		    		  System.out.println("Consulta por sexo");
		    		  System.out.println("Digite el sexo:");
		    		  sexo=teclado.readLine();
		    		  sql="select * from persona where sexo='"+sexo+"'";
		    		  break;
		    	  default:
		    		  System.out.println("Opcion incorrecta!!!");
		      }//switch
		      System.out.println(sql);
		      conexion.conectar();
		      resp=conexion.obtenerDatos(sql);
		      
		      System.out.println("\nID	NOMBRE \t EDAD \t\t\t  SEXO  \n");
		      while(resp.next())
		      {
		    	  id=resp.getInt("id");
		    	  nombre= resp.getString("nombre");
		    	  edad = resp.getInt("edad");
		    	  sexo=resp.getString("sexo");
		    	  System.out.println(id+" \t "+nombre+" \t "+edad+" \t "+sexo);
		      }//while
		      conexion.Off();
	}
	
}

