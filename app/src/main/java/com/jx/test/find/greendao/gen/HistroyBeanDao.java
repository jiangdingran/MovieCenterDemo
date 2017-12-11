package com.jx.test.find.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.jx.test.find.greendao.HistroyBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HISTROY_BEAN".
*/
public class HistroyBeanDao extends AbstractDao<HistroyBean, Long> {

    public static final String TABLENAME = "HISTROY_BEAN";

    /**
     * Properties of entity HistroyBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Movieid = new Property(1, String.class, "movieid", false, "MOVIEID");
        public final static Property Moviehead = new Property(2, String.class, "moviehead", false, "MOVIEHEAD");
        public final static Property Moviename = new Property(3, String.class, "moviename", false, "MOVIENAME");
    }


    public HistroyBeanDao(DaoConfig config) {
        super(config);
    }
    
    public HistroyBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HISTROY_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"MOVIEID\" TEXT," + // 1: movieid
                "\"MOVIEHEAD\" TEXT," + // 2: moviehead
                "\"MOVIENAME\" TEXT);"); // 3: moviename
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HISTROY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HistroyBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String movieid = entity.getMovieid();
        if (movieid != null) {
            stmt.bindString(2, movieid);
        }
 
        String moviehead = entity.getMoviehead();
        if (moviehead != null) {
            stmt.bindString(3, moviehead);
        }
 
        String moviename = entity.getMoviename();
        if (moviename != null) {
            stmt.bindString(4, moviename);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HistroyBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String movieid = entity.getMovieid();
        if (movieid != null) {
            stmt.bindString(2, movieid);
        }
 
        String moviehead = entity.getMoviehead();
        if (moviehead != null) {
            stmt.bindString(3, moviehead);
        }
 
        String moviename = entity.getMoviename();
        if (moviename != null) {
            stmt.bindString(4, moviename);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HistroyBean readEntity(Cursor cursor, int offset) {
        HistroyBean entity = new HistroyBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // movieid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // moviehead
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // moviename
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HistroyBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMovieid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMoviehead(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMoviename(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HistroyBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HistroyBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HistroyBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}