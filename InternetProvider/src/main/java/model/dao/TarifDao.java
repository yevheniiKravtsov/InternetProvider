package model.dao;

import java.util.List;

import model.entity.Tarif;

public interface TarifDao extends GenericDao<Tarif> {

	List<Tarif> findAllByServiceId(int serviceId);

	List<Tarif> findSortedByNameASCWithServiceId(int serviceId);

	List<Tarif> findSortedByNameDESCWithServiceId(int serviceId);

	List<Tarif> findSortedByPriceWithServiceId(int serviceId);

}
