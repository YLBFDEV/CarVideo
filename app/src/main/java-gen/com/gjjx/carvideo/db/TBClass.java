package com.gjjx.carvideo.db;

import com.gjjx.carvideo.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "TBCLASS".
 */
public class TBClass {

    private Long id;
    private long c_id;
    private String name;
    private String summary;
    private String filename;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient TBClassDao myDao;

    private TBCourse tBCourse;
    private Long tBCourse__resolvedKey;


    public TBClass() {
    }

    public TBClass(Long id) {
        this.id = id;
    }

    public TBClass(Long id, long c_id, String name, String summary, String filename) {
        this.id = id;
        this.c_id = c_id;
        this.name = name;
        this.summary = summary;
        this.filename = filename;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTBClassDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getC_id() {
        return c_id;
    }

    public void setC_id(long c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /** To-one relationship, resolved on first access. */
    public TBCourse getTBCourse() {
        long __key = this.c_id;
        if (tBCourse__resolvedKey == null || !tBCourse__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TBCourseDao targetDao = daoSession.getTBCourseDao();
            TBCourse tBCourseNew = targetDao.load(__key);
            synchronized (this) {
                tBCourse = tBCourseNew;
            	tBCourse__resolvedKey = __key;
            }
        }
        return tBCourse;
    }

    public void setTBCourse(TBCourse tBCourse) {
        if (tBCourse == null) {
            throw new DaoException("To-one property 'c_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.tBCourse = tBCourse;
            c_id = tBCourse.getId();
            tBCourse__resolvedKey = c_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
