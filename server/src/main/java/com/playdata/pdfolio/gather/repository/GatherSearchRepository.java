package com.playdata.pdfolio.gather.repository;

import com.playdata.pdfolio.domain.dto.gather.SearchDto;
import com.playdata.pdfolio.domain.entity.gather.Gather;
import com.playdata.pdfolio.domain.response.gather.GatherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface GatherSearchRepository {
    Page<GatherResponse> findAllByCondition(
            PageRequest request,
            SearchDto searchDto
    );

    Gather findByIdIncludingUndeletedComments(Long id);

}
