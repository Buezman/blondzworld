package com.buezman.blondzworld.controller;

import com.buezman.blondzworld.entity.Tag;
import com.buezman.blondzworld.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
}
