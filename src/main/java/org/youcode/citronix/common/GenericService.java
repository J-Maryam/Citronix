package org.youcode.citronix.common;

import org.springframework.data.domain.Pageable;

public interface GenericService <T, ID, RequestDto, ResponseDto>{
    PagedResponse<ResponseDto> getAll(Pageable pageable);
    ResponseDto getById(ID id);
    ResponseDto create(RequestDto requestDto);
    ResponseDto update(ID id, RequestDto requestDto);
    void delete(ID id);
}