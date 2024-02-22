package api.service;

import api.dto.PersonDto.MoneyRequestDto;
import api.dto.PersonDto.PersonRequestDto;
import api.dto.PersonDto.PersonResponseDto;
import api.entity.Person;
import api.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PersonImpl implements PersonService{
    @Inject
    PersonRepository personRepository;
    @Override
    public List<PersonResponseDto> getAll() {
        return personRepository.findAll().stream().map(person -> (
                new PersonResponseDto(person.getPersonId(),person.getPersonName(),
                        person.getCity(),person.getMoney())
                )).toList();
    }

    @Override
    public PersonResponseDto getById(Long aLong) {
        Person person = personRepository.findById(aLong);
        return new PersonResponseDto(person.getPersonId(),person.getPersonName(),
                person.getCity(),person.getMoney());
    }

    @Override
    @Transactional
    public PersonResponseDto create(PersonRequestDto personRequestDto) {
        Person person = new Person();
        person.setPersonName(personRequestDto.personName());
        person.setCity(personRequestDto.city());
        person.setMoney(personRequestDto.money());
        personRepository.persist(person);
        return new PersonResponseDto(person.getPersonId(),person.getPersonName(),
                    person.getCity(),person.getMoney());
    }

    @Override
    @Transactional
    public PersonResponseDto update(Long aLong, PersonRequestDto personRequestDto) {
        Person idPerson = personRepository.findById(aLong);
        idPerson.setPersonName(personRequestDto.personName());
        idPerson.setCity(personRequestDto.city());
        idPerson.setMoney(personRequestDto.money());
        personRepository.persist(idPerson);
        return new PersonResponseDto(idPerson.getPersonId(),idPerson.getPersonName(),idPerson.getCity(),
                idPerson.getMoney());
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        personRepository.deleteById(aLong);
    }

    private Double calculationMoney(Double money1, Double money2){
        return money1 + money2;

    }
    @Override
    @Transactional
    public PersonResponseDto topupMoney(Long personId, MoneyRequestDto moneyRequestDto) {
        Person personMoney= personRepository.findById(personId);
        Double resultMoney =  calculationMoney(personMoney.getMoney(), moneyRequestDto.money());
        personMoney.setMoney(personMoney.getMoney() + moneyRequestDto.money());
        personRepository.persist(personMoney);
        return new PersonResponseDto(personMoney.getPersonId(),personMoney.getPersonName(),
                personMoney.getCity(),personMoney.getMoney());
    }
}
