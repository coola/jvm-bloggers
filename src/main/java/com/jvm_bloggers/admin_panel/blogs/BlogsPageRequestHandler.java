package com.jvm_bloggers.admin_panel.blogs;

import com.jvm_bloggers.admin_panel.PaginationConfiguration;
import com.jvm_bloggers.core.data_fetching.blogs.domain.Blog;
import com.jvm_bloggers.core.data_fetching.blogs.domain.BlogRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BlogsPageRequestHandler implements IDataProvider<Blog> {

    private PaginationConfiguration paginationConfiguration;

    private BlogRepository blogRepository;

    @Override
    public Iterator<? extends Blog> iterator(long first, long count) {
        int page = Long.valueOf(first / paginationConfiguration.getDefaultPageSize()).intValue();
        return blogRepository
                .findAllByOrderByAuthorAsc(new PageRequest(page,
                        paginationConfiguration.getDefaultPageSize())
                ).iterator();
    }

    @Override
    public long size() {
        return blogRepository.count();
    }

    @Override
    public IModel<Blog> model(Blog blog) {
        return new BlogModel(blog);
    }

    @Override
    public void detach() {

    }
}
