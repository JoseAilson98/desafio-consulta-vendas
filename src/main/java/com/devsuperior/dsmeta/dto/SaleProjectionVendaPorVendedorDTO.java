package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleProjectionVendaPorVendedor;

public class SaleProjectionVendaPorVendedorDTO {

    private String sellerName;
    private Double total;


    public SaleProjectionVendaPorVendedorDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleProjectionVendaPorVendedorDTO(SaleProjectionVendaPorVendedor projection) {
        sellerName = projection.getName();
        total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
