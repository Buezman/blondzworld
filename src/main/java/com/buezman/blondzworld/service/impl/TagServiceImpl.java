package com.buezman.blondzworld.service.impl;

import com.buezman.blondzworld.entity.Tag;
import com.buezman.blondzworld.exception.ResourceNotFoundException;
import com.buezman.blondzworld.repository.TagRepository;
import com.buezman.blondzworld.service.AuthService;
import com.buezman.blondzworld.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final AuthService authService;
    private final TagRepository tagRepository;

    @Override
    public Tag createTag(Tag tag) {
        authService.validateAdmin();
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public String deleteTag(Long tagId) {
        authService.validateAdmin();
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "ID", ""+tagId));
        tagRepository.delete(tag);
        return String.format("'%s' category deleted successfully", tag.getName());
    }
}
