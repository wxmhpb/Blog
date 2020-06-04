package com.boke.service;

import com.boke.pojo.Tag;
import com.boke.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> ListTag(Pageable pageable);
    List<Tag> listTag();
    Tag updateTag(Long id, Tag tag);
    Tag getTagByName(String name);
    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    void deleteTag(Long id);
}
