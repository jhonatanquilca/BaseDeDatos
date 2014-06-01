package com.example.basededatos;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

public class contactosController {
	Context ctx;
	AdaptadorBD db;

	public contactosController(Context nctx) {
		this.ctx = nctx;
		this.db = new AdaptadorBD(ctx);
	}

	public void actionCreate(String nombre, String email) {
		db.abrir();
		db.insertarContacto(nombre, email);
		Toast.makeText(ctx, "Se ha insetado " +nombre+ " con Exito!!!!", Toast.LENGTH_LONG).show();
		db.cerrar();

	}

	public void actionDelete(int id) {
		db.abrir();
		if (db.borrarContacto(id)) {
			Toast.makeText(ctx, "Se ha eliminado con exito!!",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(ctx, "Se ha podido eliminar!!" + id,
					Toast.LENGTH_LONG).show();
		}

		db.cerrar();

	}

	public void actionUpdate(String nombre, String email) {
		if (db.actualizarContacto(1, nombre, email)) {
			Toast.makeText(ctx, "Se actualizo Correctamente!!",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(ctx, "NO se ha actualizado !!", Toast.LENGTH_LONG)
					.show();
		}

		db.cerrar();

	}

	public void actionView(int id) {
		db.abrir();
		Cursor c = db.obtenerContacto(id);
		if (c.moveToFirst()) {
			do {
				DisplayContact(c);
			} while (c.moveToNext());
		} else {
			Toast.makeText(ctx, "No se ha encontrado el contacto",
					Toast.LENGTH_LONG).show();
		}
		db.cerrar();
	}

	
	public void DisplayContact(Cursor c) {
		// TODO Auto-generated method stub
		Toast.makeText(
				ctx,
				"id: " + c.getString(0) + "\t" + "nombre: " + c.getString(1)
						+ "\t" + "email: " + c.getString(2) + "\t",
				Toast.LENGTH_LONG).show();

	}

	// añadir contactos
	// db.abrir();
	// long id=db.insertarContacto("Luis", "pedro@ex.com");
	// Toast.makeText(this, "se ha insetado "+id, Toast.LENGTH_LONG).show();
	// id=db.insertarContacto("Marco", "juan@ex.com");
	// Toast.makeText(this, "se ha insetado "+id, Toast.LENGTH_LONG).show();
	// db.cerrar();

	// ---obtener todos los contactos
	// db.abrir();
	// Cursor c=db.obtenerTodosLosContactos();
	// if(c.moveToFirst()){
	//
	// do{
	// DisplayContact(c);
	// }while(c.moveToNext());
	// }
	// db.cerrar();

	// --- obteber un contacto
	// db.abrir();
	// Cursor c=db.obtenerContacto(1);
	// if(c.moveToFirst()){
	// do{
	// DisplayContact(c);
	// }while(c.moveToNext());
	// }else{
	// Toast.makeText(this, "No se ha encontrado el contacto",
	// Toast.LENGTH_LONG).show();
	// }
	// db.cerrar();

	// --Actualizar un Contacto
	// db.abrir();
	// if(db.actualizarContacto(1, "Cosmet ", "lalalalal")){
	// Toast.makeText(this,
	// "Se actualizo Correctamente!!",Toast.LENGTH_LONG).show();
	// }else{
	// Toast.makeText(this, "NO se actualizo !!",Toast.LENGTH_LONG).show();
	// }
	// db.cerrar();

	// --Actualizar un Contacto
	// db.abrir();
	// if(db.borrarContacto(1)){
	// Toast.makeText(this, "Se Elimino !!",Toast.LENGTH_LONG).show();
	// }else{
	// Toast.makeText(this, "No se Elimino !!",Toast.LENGTH_LONG).show();
	// }
	//
	// db.cerrar();
	//

}
