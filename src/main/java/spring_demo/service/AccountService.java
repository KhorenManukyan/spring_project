package spring_demo.service;

public interface AccountService {
    void transfer(long accountId, long otherAccountId, double balance);
}
