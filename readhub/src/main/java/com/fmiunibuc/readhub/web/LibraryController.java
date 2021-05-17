package com.fmiunibuc.readhub.web;

import com.fmiunibuc.readhub.model.Library;
import com.fmiunibuc.readhub.model.repositories.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryController {
    private final Logger log = LoggerFactory.getLogger(LibraryController.class);
    private LibraryRepository libraryRepository;

    public LibraryController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @GetMapping("/libraries")
    Collection<Library> libraries(){
        return libraryRepository.findAll();
    }

    @GetMapping("/library/{id}")
    ResponseEntity<?> getLibrary(@PathVariable Long id) {
        Optional<Library> library = libraryRepository.findById(id);
        return library.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/library")
    ResponseEntity<Library> createLibrary(@Valid @RequestBody Library library) throws URISyntaxException {
        log.info("Request to create library: {}", library);
        Library result = libraryRepository.save(library);
        return ResponseEntity.created(new URI("/api/library/" + result.getId()))
                .body(result);
    }

    @PutMapping("/library/{id}")
    ResponseEntity<Library> updateLibrary(@Valid @RequestBody Library library) {
        log.info("Request to update library: {}", library);
        Library result = libraryRepository.save(library);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/library/{id}")
    public ResponseEntity<?> deleteLibrary(@PathVariable Long id) {
        log.info("Request to delete library: {}", id);
        libraryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
