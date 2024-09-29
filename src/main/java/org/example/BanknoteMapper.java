package org.example;

import org.example.controller.response.BanknoteResponse;
import org.example.entity.Banknote;
import org.example.service.BanknoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BanknoteMapper {
    BanknoteDto banknoteToBanknoteDto(Banknote banknote);

    Banknote banknoteDtoToBanknote(BanknoteDto banknoteDto);

    BanknoteResponse banknoteDtoToBanknoteResponse(BanknoteDto banknoteDto);

}
