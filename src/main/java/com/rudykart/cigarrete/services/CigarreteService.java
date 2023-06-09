package com.rudykart.cigarrete.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rudykart.cigarrete.dto.CigarreteDto;
import com.rudykart.cigarrete.dto.response.DataResponse;
import com.rudykart.cigarrete.dto.response.PagingResponse;
import com.rudykart.cigarrete.entities.Brand;
import com.rudykart.cigarrete.entities.Cigarrete;
import com.rudykart.cigarrete.repositories.BrandRepository;
import com.rudykart.cigarrete.repositories.CigarreteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CigarreteService {

    @Autowired
    private CigarreteRepository cigarreteRepository;
    @Autowired
    private BrandRepository brandRepository;

    public PagingResponse<Cigarrete> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Cigarrete> cigarretes = cigarreteRepository.findAll(pageable);

        PagingResponse<Cigarrete> response = new PagingResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("List Of Cigarretes");
        response.setPayload(cigarretes.getContent());
        response.setPageNo(cigarretes.getNumber());
        response.setPageSize(cigarretes.getSize());
        response.setTotalElements(cigarretes.getTotalElements());
        response.setTotalPages(cigarretes.getTotalPages());
        response.setLast(cigarretes.isLast());
        return response;
    }

    public DataResponse<Cigarrete> getById(Long id) {
        Cigarrete cigarrete = cigarreteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("cigarrete", id));
        return new DataResponse<>(HttpStatus.OK.value(), "cigarrete", cigarrete);
    }

    public DataResponse<Cigarrete> create(CigarreteDto cigarreteDto) {
        Brand brand = brandRepository.findById(cigarreteDto.getBrandId())
                .orElseThrow(() -> new ObjectNotFoundException("brand", cigarreteDto.getBrandId()));
        // Cigarrete cigarrete = cigarreteRepository
        // .save(Cigarrete.builder().name(cigarreteDto.getName()).value(cigarreteDto.getValue())
        // .price(cigarreteDto.getPrice()).brand(brand).build());
        Cigarrete cigarrete = new Cigarrete();
        cigarrete.setName(cigarreteDto.getName());
        cigarrete.setValue(cigarreteDto.getValue());
        cigarrete.setPrice(cigarreteDto.getPrice());
        cigarrete.setBrand(brand);
        return new DataResponse<>(HttpStatus.CREATED.value(), "cigarrete created", cigarreteRepository.save(cigarrete));
    }

    // public DataResponse<Cigarrete> create(Long brandId, CigarreteDto
    // cigarreteDto) {
    // Brand brand = brandRepository.findById(brandId)
    // .orElseThrow(() -> new ObjectNotFoundException("brand",
    // cigarreteDto.getBrandId()));
    // Cigarrete cigarrete =
    // Cigarrete.builder().name(cigarreteDto.getName()).value(cigarreteDto.getValue())
    // .price(cigarreteDto.getPrice()).brand(brand).build();
    // return new DataResponse<>(HttpStatus.CREATED.value(), "cigarrete created",
    // cigarreteRepository.save(cigarrete));
    // }

    public DataResponse<Cigarrete> update(Long cigarreteId, CigarreteDto cigarreteDto) {
        Brand brand = brandRepository.findById(cigarreteDto.getBrandId())
                .orElseThrow(() -> new ObjectNotFoundException("brand", cigarreteDto.getBrandId()));

        Cigarrete cigarrete = cigarreteRepository.findById(cigarreteId)
                .orElseThrow(() -> new ObjectNotFoundException("brand", cigarreteId));

        cigarrete.setName(cigarreteDto.getName());
        cigarrete.setValue(cigarreteDto.getValue());
        cigarrete.setPrice(cigarreteDto.getPrice());
        cigarrete.setBrand(brand);

        return new DataResponse<>(HttpStatus.OK.value(), "cigarrete updated", cigarreteRepository.save(cigarrete));
    }

    public DataResponse<String> delete(Long id) {
        Cigarrete cigarrete = cigarreteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("cigarrete", id));
        cigarreteRepository.delete(cigarrete);
        return new DataResponse<>(HttpStatus.OK.value(), "cigarrete deleted", null);
    }

}
