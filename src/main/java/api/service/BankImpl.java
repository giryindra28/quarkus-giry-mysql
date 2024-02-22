package api.service;

import api.dto.BankDto.BankRequestDto;
import api.dto.BankDto.BankResponseDto;
import api.entity.Bank;
import api.repository.BankRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BankImpl implements BankService{

    @Inject
    BankRepository bankRepository;
    @Override
    public List<BankResponseDto> getAll() {
        return bankRepository.findAll().stream().map(bank -> (
                new BankResponseDto(bank.getBankId(),bank.getBankName(),
                        bank.getDescription(),bank.getStatusBank())
                )).toList();
    }

    @Override
    public BankResponseDto getById(Long id) {
        Bank bank = bankRepository.findById(id);
        return new BankResponseDto(bank.getBankId(),
                bank.getDescription(), bank.getDescription(), bank.getStatusBank());
    }

    @Override
    @Transactional
    public BankResponseDto create(BankRequestDto bankRequestDto) {
        Bank bank = new Bank();
        bank.setBankName(bankRequestDto.bankName());
        bank.setDescription(bankRequestDto.description());
        bank.setStatusBank(bankRequestDto.statusBank());
        bankRepository.persist(bank);
        return new BankResponseDto(bank.getBankId(),bank.getBankName(),
                bank.getDescription(), bank.getStatusBank());
    }

    @Transactional
    @Override
    public BankResponseDto update(Long id, BankRequestDto bankRequestDto) {
        Bank bankDataId = bankRepository.findById(id);
        bankDataId.setBankName(bankRequestDto.bankName());
        bankDataId.setDescription(bankRequestDto.description());
        bankDataId.setStatusBank(bankRequestDto.statusBank());
        bankRepository.persist(bankDataId);
        return new BankResponseDto(bankDataId.getBankId(), bankDataId.getBankName(),
                bankDataId.getDescription(), bankDataId.getStatusBank());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        bankRepository.deleteById(id);
    }
}
