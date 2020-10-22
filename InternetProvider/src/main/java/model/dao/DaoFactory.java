package model.dao;

import model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {

	private static DaoFactory daoFactory;

	public abstract UserDao createUserDao();
	public abstract ServiceDao createServiceDao();
	public abstract TarifDao createTarifDao();
    public static DaoFactory getInstance(){
            if( daoFactory == null ){
                synchronized (DaoFactory.class){
                    if(daoFactory==null){
                        DaoFactory temp = new JDBCDaoFactory();
                        daoFactory = temp;
                    }
                }
            }
            return daoFactory;
        }
}
