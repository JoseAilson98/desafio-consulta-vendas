package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleProjectionRelatorioVendas;

public class SaleProjectionRelatorioVendasDTO {
    private Long id;
    private String date;
    private String amount;
    private String sellerName;

    public SaleProjectionRelatorioVendasDTO(Long id, String SellerName, String amount, String date) {
        this.id = id;
        this.sellerName = SellerName;
        this.amount = amount;
        this.date = date;
    }

    public SaleProjectionRelatorioVendasDTO(SaleProjectionRelatorioVendas projectionMin) {
        id = projectionMin.getId();
        sellerName = projectionMin.getName();
        amount = projectionMin.getAmount();
        date = projectionMin.getDate();
    }

    public Long getId() {
        return id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
