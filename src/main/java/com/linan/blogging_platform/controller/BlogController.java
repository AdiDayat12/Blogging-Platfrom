package com.linan.blogging_platform.controller;

import com.linan.blogging_platform.dao.BlogRequest;
import com.linan.blogging_platform.dao.BlogResponse;
import com.linan.blogging_platform.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Valid
public class BlogController {
    private final BlogService blogService;

    public BlogController (BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogResponse> save (@RequestBody BlogRequest request) {
        BlogResponse response = blogService.save(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> update (@PathVariable Long id, @RequestBody BlogRequest request) {
        BlogResponse response = blogService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> get (@PathVariable Long id) {
        BlogResponse response = blogService.get(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<BlogResponse>> getAll () {
        List<BlogResponse> blogResponseList = blogService.getAll();
        return ResponseEntity.ok(blogResponseList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BlogResponse>> getByTerm (@RequestParam("term") String term) {
        List<BlogResponse> blogResponseList = blogService.getAllByTag(term);
        return ResponseEntity.ok(blogResponseList);
    }

}
