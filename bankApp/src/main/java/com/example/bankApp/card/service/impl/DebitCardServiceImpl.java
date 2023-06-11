package com.example.bankApp.card.service.impl;

import com.example.bankApp.account.service.CheckingAccountService;
import com.example.bankApp.account.entity.CheckingAccount;
import com.example.bankApp.card.core.dto.DebitCardResponse;
import com.example.bankApp.card.core.dto.requests.CreateDebitCardRequest;
import com.example.bankApp.card.core.mapper.DebitCardMapper;
import com.example.bankApp.card.entity.DebitCard;
import com.example.bankApp.card.entity.enums.CardStatus;
import com.example.bankApp.card.repository.DebitCardRepository;
import com.example.bankApp.card.service.DebitCardService;
import com.example.bankApp.common.core.exception.AlreadyExistsException;
import com.example.bankApp.common.core.exception.EntityNotFoundException;
import com.example.bankApp.common.core.message.DebitCardMessage;
import com.example.bankApp.common.core.result.DataResult;
import com.example.bankApp.common.core.result.GeneralResult;
import com.example.bankApp.common.core.utils.UniqueNoCreator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DebitCardServiceImpl implements DebitCardService {
    private final DebitCardRepository debitCardRepository;
    private final CheckingAccountService checkingAccountService;
    private final UniqueNoCreator uniqueNoCreator;

    @Override
    @Transactional
    public GeneralResult create(int checkingAccountId, CreateDebitCardRequest createDebitCardRequest) throws EntityNotFoundException, AlreadyExistsException {
        CheckingAccount checkingAccount = checkingAccountService.findById(checkingAccountId);
        if (checkingAccount.getDebitCard() != null) {
            throw new AlreadyExistsException(DebitCardMessage.ALREADY_EXISTS.toString());
        }
        DebitCard debitCard = DebitCardMapper.MAPPER.requestToEntity(createDebitCardRequest);

        debitCard.setCardNumber(uniqueNoCreator.createAccountNo());

        Random random = new Random();
        int cvv = random.nextInt(900) + 100;
        debitCard.setCvv(String.valueOf(cvv));

        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.YEAR, 5);
        debitCard.setExpiryDate(expiry.getTime());

        debitCard.setCardStatus(CardStatus.ACTIVE);
        debitCard.setCheckingAccount(checkingAccount);

        debitCardRepository.save(debitCard);

        checkingAccount.setDebitCard(debitCard);

        DebitCardResponse debitCardResponse = DebitCardMapper.MAPPER.entityToResponse(debitCard);

        return new DataResult<>(debitCardResponse);
    }

    @Override
    public GeneralResult delete(int debitCardId) throws EntityNotFoundException {
        if (!debitCardRepository.existsById(debitCardId)) {
            throw new EntityNotFoundException(DebitCardMessage.NOT_FOUND.toString());
        }

        debitCardRepository.deleteById(debitCardId);
        return new GeneralResult("başarıyla silindi");
    }
}
