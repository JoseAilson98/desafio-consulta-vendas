package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleProjectionRelatorioVendasDTO;
import com.devsuperior.dsmeta.dto.SaleProjectionVendaPorVendedorDTO;
import com.devsuperior.dsmeta.projections.SaleProjectionRelatorioVendas;
import com.devsuperior.dsmeta.projections.SaleProjectionVendaPorVendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleProjectionRelatorioVendasDTO> relatorioVenda(String minDate, String maxDate, String name, Pageable pageable){
		String inicio = systemDateValidacaoMin(minDate);
		String maximo = systemDateValidacaoMax(maxDate);

		Page<SaleProjectionRelatorioVendas> list = repository.relatorioVenda(inicio,maximo,name,pageable);
		Page<SaleProjectionRelatorioVendasDTO> result = list.map(x -> new SaleProjectionRelatorioVendasDTO(x));
		return result;
	}


	public List<SaleProjectionVendaPorVendedorDTO> vendaPorVendedor (String minDate, String maxDate){
		String inicio = systemDateValidacaoMin(minDate);
		String maximo = systemDateValidacaoMax(maxDate);

		List<SaleProjectionVendaPorVendedor> list = repository.vendaPorVendedor(inicio,maximo);

		List<SaleProjectionVendaPorVendedorDTO> result = list.stream()
				.map(x -> new SaleProjectionVendaPorVendedorDTO(x))
				.collect(Collectors.toList());

		return result;
	}

	public String systemDateValidacaoMin (String minDate){
		String dateInicio = minDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if(!dateInicio.isEmpty()){
			LocalDate date1 = LocalDate.parse(dateInicio);
			String dateString = formatter.format(date1).toString();
			return dateString;
		}
		LocalDate date1 = LocalDate.ofInstant(Instant.now(),ZoneId.systemDefault());
		LocalDate date2 = date1.minusYears(1L);
		String dateString = formatter.format(date2).toString();
		return dateString;
	}

	public String systemDateValidacaoMax (String maxDate){
		String dateMax = maxDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if(!dateMax.isEmpty()){
			LocalDate date1 = LocalDate.parse(dateMax);
			String dateString = formatter.format(date1).toString();
			return dateString;
		}
		LocalDate date1 = LocalDate.ofInstant(Instant.now(),ZoneId.systemDefault());
		String dateString = formatter.format(date1).toString();
		return dateString;
	}
}
