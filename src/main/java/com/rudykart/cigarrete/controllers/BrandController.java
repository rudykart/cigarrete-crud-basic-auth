package com.rudykart.cigarrete.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rudykart.cigarrete.dto.BrandDto;
import com.rudykart.cigarrete.dto.response.DataResponse;
import com.rudykart.cigarrete.dto.response.PagingResponse;
import com.rudykart.cigarrete.entities.Brand;
import com.rudykart.cigarrete.services.BrandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<PagingResponse<Brand>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        PagingResponse<Brand> brands = brandService.getAllBrands(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK.value()).body(brands);
    }

    // @GetMapping
    // public ResponseEntity<DataResponse<List<Brand>>> getAll() {
    // DataResponse<List<Brand>> brands = brandService.getAll();
    // return ResponseEntity.status(HttpStatus.OK.value()).body(brands);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Brand>> getById(@PathVariable Long id) {
        DataResponse<Brand> brand = brandService.getById(id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(brand);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Brand>> create(@Valid @RequestBody BrandDto brandDto) {
        DataResponse<Brand> brand = brandService.create(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Brand>> update(@Valid @RequestBody BrandDto brandDto,
            @PathVariable("id") Long id) {
        DataResponse<Brand> brand = brandService.update(brandDto, id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(brand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> delete(@PathVariable("id") Long id) {
        DataResponse<String> brand = brandService.delete(id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(brand);
    }
}
