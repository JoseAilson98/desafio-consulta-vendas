package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SaleProjectionRelatorioVendas;
import com.devsuperior.dsmeta.projections.SaleProjectionVendaPorVendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {



    @Query(nativeQuery = true,value = "SELECT " +
            "sa.id, " +
            "sa.date, " +
            "sa.amount, " +
            "se.name " +
            "FROM tb_sales sa " +
            "INNER JOIN tb_seller se " +
            "ON sa.seller_id = se.id " +
            "WHERE sa.date BETWEEN :minDate AND :maxDate AND UPPER (se.name) LIKE UPPER(CONCAT('%',:name,'%')) " +
            "ORDER BY sa.date DESC",countQuery = "SELECT " +
            "sa.id, " +
            "sa.date, " +
            "sa.amount, " +
            "se.name " +
            "FROM tb_sales sa " +
            "INNER JOIN tb_seller se " +
            "ON sa.seller_id = se.id " +
            "WHERE sa.date BETWEEN :minDate AND :maxDate AND UPPER (se.name) LIKE UPPER(CONCAT('%',:name,'%')) " +
            "ORDER BY sa.date DESC")
    Page<SaleProjectionRelatorioVendas> relatorioVenda(String minDate, String maxDate, String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT " +
            "se.name, " +
            "SUM(sa.amount) AS total " +
            "FROM tb_sales sa " +
            "INNER JOIN tb_seller se " +
            "ON sa.seller_id = se.id " +
            "WHERE sa.date BETWEEN  :minDate AND :maxDate " +
            "GROUP BY se.name " +
            "ORDER BY total DESC")
    List<SaleProjectionVendaPorVendedor> vendaPorVendedor(String minDate, String maxDate);
}
