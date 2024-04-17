package com.ra.web.Enum;

public interface ConstStatus {
    interface ProductStt {
        boolean ACTIVE = true;
        boolean INACTION = false;
    }
    interface EmpStt {
        int ACTIVE = 0;
        int SLEEP = 1;
        int QUIT = 2;
    }
    interface AccountStt {
        boolean ACTIVE = true;
        boolean BLOCK = false;
    }
    interface BillStt {
        short CREATE = 0;
        short CANCEL = 1;
        short APPROVAL = 2;
    }

}
