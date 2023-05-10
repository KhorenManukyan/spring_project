package spring_demo.service.impl;

import org.springframework.stereotype.Service;
import spring_demo.service.AccountService;

// without @Service annotation, works with configuration file
public class AccountServiceImpl implements AccountService {
    @Override
    public void transfer(long accountId, long otherAccountId, double balance) {

    }
}
