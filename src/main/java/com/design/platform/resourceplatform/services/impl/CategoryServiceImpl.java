package com.design.platform.resourceplatform.services.impl;

import com.design.platform.resourceplatform.entities.Category;
import com.design.platform.resourceplatform.mappers.CategoryMapperKt;
import com.design.platform.resourceplatform.mappers.ResourceMapperKt;
import com.design.platform.resourceplatform.repositories.CategoryRepository;
import com.design.platform.resourceplatform.repositories.ResourceRepository;
import com.design.platform.resourceplatform.services.CategoryService;
import com.design.platform.resourceplatform.transfer.CategoryBooth;
import com.design.platform.resourceplatform.transfer.CategoryDefiner;
import com.design.platform.resourceplatform.transfer.CategoryRecorder;
import com.design.platform.resourceplatform.transfer.ResourceBooth;
import com.design.platform.resourceplatform.utils.PageHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    // Autowired
    // ===============================================

    public final CategoryRepository repository;

    private final ResourceRepository resourceRepository;

    public CategoryServiceImpl(
            CategoryRepository repository,
            ResourceRepository resourceRepository) {
        this.repository = repository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public CategoryRepository repository() {
        return repository;
    }

    // Methods Get List
    // ===============================================

    @Override
    public PageHolder<CategoryBooth> GetCategoryBoothList(PageRequest request) {
        return new PageHolder<>(
                repository.findAll(request)
                          .map(CategoryMapperKt::auto));
    }

    @Override
    public PageHolder<ResourceBooth> GetCategoryResourceBoothList(int id, PageRequest request) {
        return new PageHolder<>(
                resourceRepository.findAllByCategory(GetCategory(id), request)
                                  .map(ResourceMapperKt::auto));
    }

    // Methods Get Item
    // ===============================================

    @Override
    public CategoryBooth GetCategoryBooth(int id) {
        return CategoryMapperKt.auto(GetCategory(id));
    }

    @Override
    public Category GetCategory(int id) {
        return repository.findById(id).orElseThrow();
    }

    // Methods Modify
    // ===============================================

    @Override
    public Category UpdateCategory(CategoryRecorder recorder) {
        return repository.save(recorder.ToCategory(this));
    }

    @Override
    public Category CreateCategory(CategoryDefiner definer) {
        return repository.save(definer.ToCategory());
    }

    @Override
    public void DeleteCategory(int id) {
        repository.deleteById(id);
    }
}
