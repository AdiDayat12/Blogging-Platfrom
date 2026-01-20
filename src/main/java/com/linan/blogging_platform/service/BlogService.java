package com.linan.blogging_platform.service;

import com.linan.blogging_platform.dao.BlogRequest;
import com.linan.blogging_platform.dao.BlogResponse;

import java.util.List;

public interface BlogService {
    BlogResponse save (BlogRequest request);
    BlogResponse update (Long id, BlogRequest request);
    void delete (Long id);
    BlogResponse get (Long id);
    List<BlogResponse> getAll ();
    List<BlogResponse> getAllByTag (String tag);
}
