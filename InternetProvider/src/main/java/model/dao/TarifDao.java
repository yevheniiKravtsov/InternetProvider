package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.entity.Tarif;

public interface TarifDao extends GenericDao<Tarif> {

	List<Tarif> findAllByServiceId(int serviceId) throws SQLException;

	List<Tarif> findSortedByNameASCWithServiceId(int serviceId) throws Exception;

	List<Tarif> findSortedByNameDESCWithServiceId(int serviceId) throws Exception;

	List<Tarif> findSortedByPriceWithServiceId(int serviceId) throws Exception;

}
