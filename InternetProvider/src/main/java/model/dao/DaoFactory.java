package model.dao;

import model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {

	private static DaoFactory daoFactory;

	public abstract UserDao createUserDao() throws Exception;
	public abstract ServiceDao createServiceDao() throws Exception;
	public abstract TarifDao createTarifDao() throws Exception;
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
