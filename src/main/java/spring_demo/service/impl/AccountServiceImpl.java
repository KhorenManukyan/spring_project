//package spring_demo.service.impl;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import spring_demo.model.Account;
//import spring_demo.repository.AccountRepository;
//import spring_demo.service.AccountService;
//
//@Service
//@RequiredArgsConstructor
//public class AccountServiceImpl implements AccountService {
//
//    private final AccountRepository accountRepository;
//
//    @Override
//    @Transactional
//    public void transfer(int accountId, int otherAccountId, double balance) {
//        Account account = accountRepository.findById(accountId).orElse(null);
//        Account otherAccount = accountRepository.findById(otherAccountId).orElse(null);
//
//        assert account != null;
//        account.setBalance(account.getBalance() - balance);
//
//        assert otherAccount != null;
//        otherAccount.setBalance(otherAccount.getBalance() + balance);
//
//        accountRepository.save(account);
//        accountRepository.save(otherAccount);
//    }
//}
