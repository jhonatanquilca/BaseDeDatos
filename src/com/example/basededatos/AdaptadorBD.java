package com.example.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdaptadorBD {
	public static final String KEY_IDFILA = "_id";
	public static final String KEY_NOMBRE = "nombre";
	public static final String KEY_EMAIL = "email";
	public static final String TAG = "AdaptadorBD";
	public static final String NOMBRE_BASEDEDATOS = "MiDB";
	public static final String TABLA_BASEDEDATOS = "contactos";
	public static final int VERSION_BASEDEDATOS = 1;
	public static final String CREAR_BASEDEDATOS = "create table contactos(_id integer primary key autoincrement,"
			+ "nombre text not null," + "email text not null);";
	public final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public AdaptadorBD(Context ctx) {
		// TODO Auto-generated constructor stub
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	public static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, NOMBRE_BASEDEDATOS, null, VERSION_BASEDEDATOS);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(CREAR_BASEDEDATOS);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w(TAG, "Actualizando base de datos de version" + oldVersion
					+ " a " + newVersion
					+ ", loque destruira todos los viejos datos");
			db.execSQL("drop table if exist contactos");
			onCreate(db);

		}
	}

	// abrir base de datos
	public AdaptadorBD abrir() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// cerrar base de datos
	public void cerrar() {
		DBHelper.close();
	}

	// insertar un contacto en la base de datos
	public long insertarContacto(String nombre, String email) {
		ContentValues valoresIniciales = new ContentValues();
		valoresIniciales.put(KEY_NOMBRE, nombre);
		valoresIniciales.put(KEY_EMAIL, email);
		return db.insert(TABLA_BASEDEDATOS, null, valoresIniciales);
	}

	// eliminar un contacto
	public boolean borrarContacto(long idFila) {
		return db.delete(TABLA_BASEDEDATOS, KEY_IDFILA + "=" + idFila, null) > 0;
	}

	// recuperar todos los contactos
	public Cursor obtenerTodosLosContactos() {
		return db.query(TABLA_BASEDEDATOS, new String[] { KEY_IDFILA,
				KEY_NOMBRE, KEY_EMAIL }, null, null, null, null, null);
	}

	// recuperar un contacto especifico
	public Cursor obtenerContacto(long idFila) throws SQLException {
		Cursor mCursor = db.query(true, TABLA_BASEDEDATOS, new String[] {
				KEY_IDFILA, KEY_NOMBRE, KEY_EMAIL }, KEY_IDFILA + "=" + idFila,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		return mCursor;
	}

	// actualizar un contacto en la base de datos
	public boolean actualizarContacto(int idFila, String nombre, String email) {
		ContentValues args = new ContentValues();
		args.put(KEY_NOMBRE, nombre);
		args.put(KEY_EMAIL, email);
		return db.update(TABLA_BASEDEDATOS, args, KEY_IDFILA + "=" + idFila,
				null) > 0;
	}
}
