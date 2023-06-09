package com.rudykart.cigarrete.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rudykart.cigarrete.dto.BrandDto;
import com.rudykart.cigarrete.dto.response.DataResponse;
import com.rudykart.cigarrete.dto.response.PagingResponse;
import com.rudykart.cigarrete.entities.Brand;
import com.rudykart.cigarrete.repositories.BrandRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public PagingResponse<Brand> getAllBrands(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Brand> brands = brandRepository.findAll(pageable);

        PagingResponse<Brand> response = new PagingResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("List Of Brands");
        response.setPayload(brands.getContent());
        response.setPageNo(brands.getNumber());
        response.setPageSize(brands.getSize());
        response.setTotalElements(brands.getTotalElements());
        response.setTotalPages(brands.getTotalPages());
        response.setLast(brands.isLast());
        return response;
    }

    public DataResponse<List<Brand>> getAll() {
        List<Brand> brand = brandRepository.findAll();
        return new DataResponse<>(HttpStatus.OK.value(), "Brand created", brand);
    }

    public DataResponse<Brand> getById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("brand", id));
        return new DataResponse<>(HttpStatus.OK.value(), "Brand created", brand);
    }

    public DataResponse<Brand> create(BrandDto brandDto) {
        Brand brand = brandRepository.save(Brand.builder().name(brandDto.getName()).build());
        return new DataResponse<>(HttpStatus.CREATED.value(), "Brand created", brand);
    }

    public DataResponse<Brand> update(BrandDto brandDto, Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("brand", id));
        brand.setName(brandDto.getName());
        return new DataResponse<>(HttpStatus.OK.value(), "Brand created", brand);
    }

    public DataResponse<String> delete(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("brand", id));
        brandRepository.delete(brand);
        return new DataResponse<>(HttpStatus.OK.value(), "Brand deleted", null);
    }
}
