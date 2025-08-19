package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleProjectionRelatorioVendasDTO;
import com.devsuperior.dsmeta.dto.SaleProjectionVendaPorVendedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleProjectionRelatorioVendasDTO>> getReport(
			@RequestParam(name = "minDate",defaultValue = "") String minDate,
			@RequestParam(name = "maxDate",defaultValue = "") String maxDate,
			@RequestParam(name = "name", defaultValue = "") String name,
			Pageable pageable) {
		Page<SaleProjectionRelatorioVendasDTO> dto = service.relatorioVenda(minDate,maxDate,name,pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleProjectionVendaPorVendedorDTO>> getSummary(
			@RequestParam(name = "minDate", defaultValue = "") String minDate,
			@RequestParam(name = "maxDate",defaultValue = "") String maxDate) {

		List<SaleProjectionVendaPorVendedorDTO> dto = service.vendaPorVendedor(minDate,maxDate);
		return ResponseEntity.ok(dto);
	}
}
