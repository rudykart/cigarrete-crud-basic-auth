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

import com.rudykart.cigarrete.dto.CigarreteDto;
import com.rudykart.cigarrete.dto.response.DataResponse;
import com.rudykart.cigarrete.dto.response.PagingResponse;
import com.rudykart.cigarrete.entities.Cigarrete;
import com.rudykart.cigarrete.services.CigarreteService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/cigarretes")
public class CigarreteController {
    @Autowired
    private CigarreteService cigarreteService;

    @GetMapping
    public ResponseEntity<PagingResponse<Cigarrete>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        PagingResponse<Cigarrete> cigarretes = cigarreteService.getAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK.value()).body(cigarretes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Cigarrete>> getById(@PathVariable("id") Long id) {
        DataResponse<Cigarrete> cigarrete = cigarreteService.getById(id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(cigarrete);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Cigarrete>> create(@Valid @RequestBody CigarreteDto cigarreteDto) {
        DataResponse<Cigarrete> cigarrete = cigarreteService.create(cigarreteDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(cigarrete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Cigarrete>> update(@PathParam("id") Long id,
            @Valid @RequestBody CigarreteDto cigarreteDto) {
        DataResponse<Cigarrete> cigarrete = cigarreteService.update(id, cigarreteDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(cigarrete);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> delete(@PathParam("id") Long id) {
        DataResponse<String> cigarrete = cigarreteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(cigarrete);
    }
}
