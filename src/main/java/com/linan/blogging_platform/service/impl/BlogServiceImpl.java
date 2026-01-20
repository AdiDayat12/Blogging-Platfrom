package com.linan.blogging_platform.service.impl;

import com.linan.blogging_platform.dao.BlogRequest;
import com.linan.blogging_platform.dao.BlogResponse;
import com.linan.blogging_platform.entity.Blog;
import com.linan.blogging_platform.errors.BlogNotFound;
import com.linan.blogging_platform.repository.BlogRepository;
import com.linan.blogging_platform.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public BlogResponse save(BlogRequest request) {
        Blog blog = toEntity(request);
        blogRepository.save(blog);
        System.out.println("saved title: " + blog.getTitle());
        return entityToResponse(blog);
    }

    @Override
    public BlogResponse update(Long id, BlogRequest request) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFound("Not found"));
        existingBlog.setTitle(request.getTitle());
        existingBlog.setContent(request.getContent());
        existingBlog.setCategory(request.getCategory());
        existingBlog.setTags(request.getTags());

        blogRepository.save(existingBlog);

        return entityToResponse(existingBlog);
    }

    @Override
    public void delete(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new BlogNotFound("Not found");
        }
        blogRepository.deleteById(id);
    }

    @Override
    public BlogResponse get(Long id) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFound("Not found"));

        return entityToResponse(existingBlog);
    }

    @Override
    public List<BlogResponse> getAll() {
        return blogRepository.findAll()
                .stream()
                .map(this::entityToResponse)
                .toList();
    }

    @Override
    public List<BlogResponse> getAllByTag (String tags) {
        String param = capitalizeFirst(tags);
        return blogRepository.findAllByTag(param)
                .stream()
                .map(this::entityToResponse)
                .toList();
    }

    private Blog toEntity (BlogRequest request){
        Blog blog = new Blog();
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blog.setCategory(request.getCategory());
        Set<String> tags = request.getTags()
                .stream()
                .map(this::capitalizeFirst)
                .collect(Collectors.toSet());
        blog.setTags(tags);
        return blog;
    }


    // There is no diff between the real object and dao response, but
    // since there might be updates in future I create this
    private BlogResponse entityToResponse (Blog blog){
        BlogResponse response = new BlogResponse();
        response.setId(blog.getId());
        response.setTitle(blog.getTitle());
        response.setContent(blog.getContent());
        response.setCategory(blog.getCategory());
        response.setTags(blog.getTags());
        response.setCreatedAt(blog.getCreatedAt());
        response.setUpdatedAt(blog.getUpdatedAt());

        return response;
    }

    private String capitalizeFirst (String input) {
        String firstChar = input.substring(0,1).toUpperCase();
        String remainChars = input.substring(1);
        return firstChar + remainChars;
    }
}
