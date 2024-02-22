package api.service;

import api.dto.PersonDto.MoneyRequestDto;
import api.dto.PersonDto.PersonRequestDto;
import api.dto.PersonDto.PersonResponseDto;

public interface PersonService extends BaseServiceCrud<PersonResponseDto, PersonRequestDto, Long>{
    PersonResponseDto topupMoney(Long personId, MoneyRequestDto moneyRequestDto);
}
