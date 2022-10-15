package com.buezman.blondzworld.service;

import com.buezman.blondzworld.entity.Tag;

public interface TagService {
    Tag createTag(Tag tag);
    String deleteTag(Long tagId);
}
